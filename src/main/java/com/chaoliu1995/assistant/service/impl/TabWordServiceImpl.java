package com.chaoliu1995.assistant.service.impl;

import com.chaoliu1995.assistant.config.Config;
import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.UserBook;
import com.chaoliu1995.assistant.entity.UserWord;
import com.chaoliu1995.assistant.entity.shanbay.*;
import com.chaoliu1995.assistant.mapper.*;
import com.chaoliu1995.assistant.model.*;
import com.chaoliu1995.assistant.service.TabWordService;
import com.chaoliu1995.assistant.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tabWordService")
@Transactional(readOnly = true)
public class TabWordServiceImpl implements TabWordService {
	
	private static final Logger logger = LoggerFactory.getLogger(TabWordServiceImpl.class);
	
	@Autowired
	private TabWordMapper tabWordMapper;
	
	@Autowired
	private TabPronunciationsMapper tabPronunciationsMapper;
	
	@Autowired
	private UkAudioAddressesMapper ukAudioAddressesMapper;
	
	@Autowired
	private UsAudioAddressesMapper usAudioAddressesMapper;
	
	@Autowired
	private CnDefinitionMapper cnDefinitionMapper;
	
	@Autowired
	private EnDefinitionMapper enDefinitionMapper;
	
	@Autowired
	private EnDefnAdjMapper enDefnAdjMapper;
	
	@Autowired
	private EnDefnAdvMapper enDefnAdvMapper;
	
	@Autowired
	private EnDefnArtMapper enDefnArtMapper;
	
	@Autowired
	private EnDefnConjMapper enDefnConjMapper;
	
	@Autowired
	private EnDefnInterjMapper enDefnInterjMapper;
	
	@Autowired
	private EnDefnNMapper enDefnNMapper;
	
	@Autowired
	private EnDefnNumMapper enDefnNumMapper;
	
	@Autowired
	private EnDefnPrepMapper enDefnPrepMapper;
	
	@Autowired
	private EnDefnPronMapper enDefnPronMapper;
	
	@Autowired
	private EnDefnVMapper enDefnVMapper;

    @Autowired
    private Config projectConfig;

	@Autowired
	private UserWordMapper userWordMapper;

	@Autowired
	private BookWordMapper bookWordMapper;

	@Autowired
	private UserBookMapper userBookMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void search(SearchDTO searchDTO, ResultDTO<TabWord> resultDTO) {
		ShanBayResult shanBay;
		String result;
		try {
			result = HttpUtils.get(Consts.SHAN_BAY_SEARCH_URL+searchDTO.getWord(),Consts.CHARSET);
			shanBay = StringUtils.getGson().fromJson(result,ShanBayResult.class);
		} catch (Exception e) {
			e.printStackTrace();
			resultDTO.setMessage("请求扇贝API出现异常");
			return;
		}
		if(shanBay == null){
			resultDTO.setMessage("请求扇贝API出现异常");
			return;
		}
		if(!shanBay.getMsg().equals("SUCCESS") || !shanBay.getStatus_code().equals("0")){
			resultDTO.setMessage(shanBay.getMsg());
			return;
		}
		//扇贝接口在查询 englishs 这样的单词时，会返回 english，这里做一下处理，防止二次保存
		List<TabWord> dbWords = tabWordMapper.select(new TabWord(shanBay.getData().getContent()));
		if(dbWords != null && dbWords.size() > 0){
			resultDTO.setData(dbWords.get(0));
			resultDTO.setStatus(Consts.SUCCESS);
			return;
		}
		TabWord tabWord = this.saveWord(shanBay);
		if(tabWord == null || tabWord.getId() == null){
			resultDTO.setMessage("保存单词出现异常");
			return;
		}
		UserWord userWord = new UserWord(searchDTO.getUserId(),tabWord.getId());
		UserWord tempUserWord = userWordMapper.selectOne(userWord);
		if(tempUserWord == null){
			userWord.init();
			userWordMapper.insert(userWord);
		}
		resultDTO.setData(tabWord);
		resultDTO.setStatus(Consts.SUCCESS);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TabWord saveWord(ShanBayResult sbr) {
		Word word = sbr.getData();
		logger.info("保存新的单词：" + word.getContent());
		//保存单词发音文件
		if(!StringUtils.isEmpty(word.getUk_audio())){
			try {
				FileUtils.downLoadFromUrl(word.getUk_audio(),word.getContent() + ".mp3",projectConfig.getFileAudioPath() + Consts.UK_AUDIO_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(!StringUtils.isEmpty(word.getUs_audio())){
			try {
				FileUtils.downLoadFromUrl(word.getUs_audio(),word.getContent() + ".mp3",projectConfig.getFileAudioPath() + Consts.US_AUDIO_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//将从扇贝获得的单词数据结构转换为本地数据库实体
		TabWord tabWord = EntityUtils.wordToTabWord(word);
		tabWordMapper.insert(tabWord);
		//获得单词主键
		int wordId = tabWord.getId();
		TabPronunciations tp = EntityUtils.pronunciationsToTabPronunciations(word.getPronunciations());
		tp.setWordId(wordId);
		tabPronunciationsMapper.insert(tp);
		//获取单词发音文件地址
		Audio_addresses audioAddress = word.getAudio_addresses();
		
		String[] uk = audioAddress.getUk();
		UkAudioAddresses ukAA = null;
		if(uk != null && uk.length > 0){
			for(String ukStr : uk){
				ukAA = new UkAudioAddresses();
				ukAA.setUrl(ukStr);
				ukAA.setWordId(wordId);
				ukAudioAddressesMapper.insert(ukAA);
			}
		}
		
		String[] us = audioAddress.getUs();
		UsAudioAddresses usAA = null;
		if(us != null && us.length > 0){
			for(String usStr : us){
				usAA = new UsAudioAddresses();
				usAA.setUrl(usStr);
				usAA.setWordId(wordId);
				usAudioAddressesMapper.insert(usAA);
			}
		}
		
		CnDefinition cnDefi = new CnDefinition();
		Cn_definition cn_defi = word.getCn_definition();
		cnDefi.setPos(cn_defi.getPos());
		cnDefi.setDefn(cn_defi.getDefn());
		cnDefi.setWordId(wordId);
		cnDefinitionMapper.insert(cnDefi);
		
		EnDefinition enDefi = new EnDefinition();
		En_definition en_defi = word.getEn_definition();
		enDefi.setPos(en_defi.getPos());
		enDefi.setDefn(en_defi.getDefn());
		enDefi.setWordId(wordId);
		enDefinitionMapper.insert(enDefi);
		
		En_definitions en_defis = word.getEn_definitions();
		String[] tempStrArr = null;
		if(en_defis.getAdj() != null && en_defis.getAdj().length > 0){
			tempStrArr = en_defis.getAdj();
			EnDefnAdj enDefnAdj = null;
			for(String str : tempStrArr){
				enDefnAdj = new EnDefnAdj();
				enDefnAdj.setWordId(wordId);
				enDefnAdj.setAdj(str);
				enDefnAdjMapper.insert(enDefnAdj);
			}
		}
		
		if(en_defis.getAdv() != null && en_defis.getAdv().length > 0){
			tempStrArr = en_defis.getAdv();
			EnDefnAdv enDefnAdv = null;
			for(String str : tempStrArr){
				enDefnAdv = new EnDefnAdv();
				enDefnAdv.setWordId(wordId);
				enDefnAdv.setAdv(str);
				enDefnAdvMapper.insert(enDefnAdv);
			}
		}
		
		if(en_defis.getArt() != null && en_defis.getArt().length > 0){
			tempStrArr = en_defis.getArt();
			EnDefnArt enDefnArt = null;
			for(String str : tempStrArr){
				enDefnArt = new EnDefnArt();
				enDefnArt.setWordId(wordId);
				enDefnArt.setArt(str);
				enDefnArtMapper.insert(enDefnArt);
			}
		}
		
		if(en_defis.getConj() != null && en_defis.getConj().length > 0){
			tempStrArr = en_defis.getConj();
			EnDefnConj enDefnConj = null;
			for(String str : tempStrArr){
				enDefnConj = new EnDefnConj();
				enDefnConj.setWordId(wordId);
				enDefnConj.setConj(str);
				enDefnConjMapper.insert(enDefnConj);
			}
		}
		
		if(en_defis.getInterj() != null && en_defis.getInterj().length > 0){
			tempStrArr = en_defis.getInterj();
			EnDefnInterj enDefnInterj = null;
			for(String str : tempStrArr){
				enDefnInterj = new EnDefnInterj();
				enDefnInterj.setWordId(wordId);
				enDefnInterj.setInterj(str);
				enDefnInterjMapper.insert(enDefnInterj);
			}
		}
		
		if(en_defis.getN() != null && en_defis.getN().length > 0){
			tempStrArr = en_defis.getN();
			EnDefnN enDefnN = null;
			for(String str : tempStrArr){
				enDefnN = new EnDefnN();
				enDefnN.setWordId(wordId);
				enDefnN.setN(str);
				enDefnNMapper.insert(enDefnN);
			}
		}
		
		if(en_defis.getNum() != null && en_defis.getNum().length > 0){
			tempStrArr = en_defis.getNum();
			EnDefnNum enDefnNum = null;
			for(String str : tempStrArr){
				enDefnNum = new EnDefnNum();
				enDefnNum.setWordId(wordId);
				enDefnNum.setNum(str);
				enDefnNumMapper.insert(enDefnNum);
			}
		}
		
		if(en_defis.getPrep() != null && en_defis.getPrep().length > 0){
			tempStrArr = en_defis.getPrep();
			EnDefnPrep enDefnPrep = null;
			for(String str : tempStrArr){
				enDefnPrep = new EnDefnPrep();
				enDefnPrep.setWordId(wordId);
				enDefnPrep.setPrep(str);
				enDefnPrepMapper.insert(enDefnPrep);
			}
		}
		
		if(en_defis.getPron() != null && en_defis.getPron().length > 0){
			tempStrArr = en_defis.getPron();
			EnDefnPron enDefnPron = null;
			for(String str : tempStrArr){
				enDefnPron = new EnDefnPron();
				enDefnPron.setWordId(wordId);
				enDefnPron.setPron(str);
				enDefnPronMapper.insert(enDefnPron);
			}
		}
		
		if(en_defis.getV() != null && en_defis.getV().length > 0){
			tempStrArr = en_defis.getV();
			EnDefnV enDefnV = null;
			for(String str : tempStrArr){
				enDefnV = new EnDefnV();
				enDefnV.setWordId(wordId);
				enDefnV.setV(str);
				enDefnVMapper.insert(enDefnV);
			}
		}
		return tabWord;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void randomWord(RandomWordDTO randomWordDTO, ResultDTO<WaitReviewDTO> resultDTO){
		if(randomWordDTO.getWordId() != null){
			UserWord userWord = userWordMapper.selectOne(new UserWord(randomWordDTO.getUserId(),randomWordDTO.getWordId()));
			if(userWord == null){
				resultDTO.setMessage("单词不存在");
				return;
			}
			if(randomWordDTO.getStatus() - Consts.MEMORY_STATUS_10 > 0){
				randomWordDTO.setIntervalType(Consts.DAY);
				randomWordDTO.setInterval(365);
				randomWordDTO.setStatus(Consts.MEMORY_STATUS_10);
				userWordMapper.updateShowTime(randomWordDTO);
			}else{
				int memoryStatus = 0;
				if(randomWordDTO.getStatus() - Consts.MEMORY_STATUS_10 == 0){
					memoryStatus = userWord.getMemoryStatus();
					randomWordDTO.setStatus(userWord.getMemoryStatus() + Consts.ONE);
				}else if(randomWordDTO.getStatus() - Consts.MEMORY_STATUS_10 < 0){
					memoryStatus = randomWordDTO.getStatus();
					randomWordDTO.setStatus(randomWordDTO.getStatus() + Consts.ONE);
				}
				switch (memoryStatus){
					case Consts.MEMORY_STATUS_1:
						randomWordDTO.setIntervalType(Consts.MINUTE);
						randomWordDTO.setInterval(5);
						break;
					case Consts.MEMORY_STATUS_2:
						randomWordDTO.setIntervalType(Consts.MINUTE);
						randomWordDTO.setInterval(30);
						break;
					case Consts.MEMORY_STATUS_3:
						randomWordDTO.setIntervalType(Consts.HOUR);
						randomWordDTO.setInterval(12);
						break;
					case Consts.MEMORY_STATUS_4:
						randomWordDTO.setIntervalType(Consts.DAY);
						randomWordDTO.setInterval(1);
						break;
					case Consts.MEMORY_STATUS_5:
						randomWordDTO.setIntervalType(Consts.DAY);
						randomWordDTO.setInterval(2);
						break;
					case Consts.MEMORY_STATUS_6:
						randomWordDTO.setIntervalType(Consts.DAY);
						randomWordDTO.setInterval(4);
						break;
					case Consts.MEMORY_STATUS_7:
						randomWordDTO.setIntervalType(Consts.DAY);
						randomWordDTO.setInterval(7);
						break;
					case Consts.MEMORY_STATUS_8:
						randomWordDTO.setIntervalType(Consts.DAY);
						randomWordDTO.setInterval(15);
						break;
					case Consts.MEMORY_STATUS_9:
						randomWordDTO.setIntervalType(Consts.DAY);
						randomWordDTO.setInterval(30);
						break;
				}
				userWordMapper.updateShowTime(randomWordDTO);
			}
		}

		WaitReviewDTO waitReviewDTO = new WaitReviewDTO();
		if(randomWordDTO.getBookId() != null){
			UserBook userBook = userBookMapper.selectOne(new UserBook(randomWordDTO.getUserId(),randomWordDTO.getBookId()));
			if(userBook == null){
				resultDTO.setMessage("单词本不存在");
				return;
			}
			Integer wordId = bookWordMapper.randomWordIdByCurrentTime(randomWordDTO.getUserId(),randomWordDTO.getBookId());
			if(wordId == null){
				wordId = bookWordMapper.randomWordId(randomWordDTO.getUserId(),randomWordDTO.getBookId());
				waitReviewDTO.setTotal(Consts.ZERO);
			}else{
				int total = bookWordMapper.countWaitReview(randomWordDTO.getUserId(),randomWordDTO.getBookId());
				waitReviewDTO.setTotal(total);
			}
			if(wordId == null){
				resultDTO.setMessage("单词本中还没有单词");
				return;
			}
			TabWord tabWord = tabWordMapper.selectByPrimaryKey(wordId);
			waitReviewDTO.setWord(tabWord);
			resultDTO.setData(waitReviewDTO);
			resultDTO.setStatus(Consts.SUCCESS);
			return;
		}

		Integer wordId = userWordMapper.randomWordIdByCurrentTime(randomWordDTO.getUserId());
		if(wordId == null){
			wordId = userWordMapper.randomWordId(randomWordDTO.getUserId());
			waitReviewDTO.setTotal(Consts.ZERO);
		}else{
			int total = userWordMapper.countWaitReview(randomWordDTO.getUserId());
			waitReviewDTO.setTotal(total);
		}
		if(wordId == null){
			resultDTO.setMessage("您还未查询或添加过任何一个单词");
			return;
		}
		TabWord tabWord = tabWordMapper.selectByPrimaryKey(wordId);
		waitReviewDTO.setWord(tabWord);
		resultDTO.setData(waitReviewDTO);
		resultDTO.setStatus(Consts.SUCCESS);
		return;
	}

	@Override
	public void listTabWord(ResultsDTO<TabWord> resultsDTO, CommonListDTO listDTO) {
		int total = tabWordMapper.countByCommonListDTO(listDTO);
		Pager pager = new Pager(listDTO.getPage(),listDTO.getRows(),total);
		listDTO.setStart(pager.getStart());
		listDTO.setRows(pager.getPageSize());
		List<TabWord> wordList = tabWordMapper.listByCommonListDTO(listDTO);
		resultsDTO.setTotal(total);
		resultsDTO.setData(wordList);
		resultsDTO.setStatus(Consts.SUCCESS);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void getWord(SearchDTO searchDTO, ResultDTO<TabWord> resultDTO) {
		List<TabWord> tabWordList = tabWordMapper.select(new TabWord(searchDTO.getWord()));
		if(tabWordList == null || tabWordList.size() < 1){
			return;
		}
		resultDTO.setData(tabWordList.get(0));
		UserWord userWord = new UserWord(searchDTO.getUserId(),tabWordList.get(0).getId());
		UserWord tempUserWord = userWordMapper.selectOne(userWord);
		if(tempUserWord == null){
			userWord.init();
			userWordMapper.insert(userWord);
		}
		resultDTO.setStatus(Consts.SUCCESS);
	}
}
