package com.chaoliu1995.assistant.service.impl;

import com.chaoliu1995.assistant.config.Config;
import com.chaoliu1995.assistant.dto.*;
import com.chaoliu1995.assistant.entity.CommonSet;
import com.chaoliu1995.assistant.entity.UserBook;
import com.chaoliu1995.assistant.entity.UserWord;
import com.chaoliu1995.assistant.entity.shanbay.*;
import com.chaoliu1995.assistant.mapper.*;
import com.chaoliu1995.assistant.model.*;
import com.chaoliu1995.assistant.mq.Producer;
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
	private CommonSetMapper commonSetMapper;

	@Autowired
	private Producer producer;

	@Autowired
	private UserWordMapper userWordMapper;

	@Autowired
	private BookWordMapper bookWordMapper;

	@Autowired
	private UserBookMapper userBookMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TabWord saveWord(ShanBayResult sbr) {
		Word word = sbr.getData();
		logger.info("保存新的单词：" + word.getContent());
		//保存单词发音文件
		if(!StringUtils.isEmpty(word.getUk_audio())){
			try {
				FileUtils.downLoadFromUrl(word.getUk_audio(),word.getAudio_name() + ".mp3",projectConfig.getFileAudioPath() + Consts.UK_AUDIO_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(!StringUtils.isEmpty(word.getUs_audio())){
			try {
				FileUtils.downLoadFromUrl(word.getUs_audio(),word.getAudio_name() + ".mp3",projectConfig.getFileAudioPath() + Consts.US_AUDIO_PATH);
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
	public void getWaitReviewWord(CommonIdDTO commonIdDTO, ResultDTO<WaitReviewDTO> resultDTO){
        WaitReviewDTO waitReviewDTO = new WaitReviewDTO();
        if(commonIdDTO.getCommonId() == null){
			Integer wordId = userWordMapper.getWordIdByShowTime(commonIdDTO.getUserId());
			if(wordId == null){
				resultDTO.setMessage("所有待复习的单词已全部复习完成");
				resultDTO.setStatus(Consts.SUCCESS);
				return;
			}
			TabWord tabWord = tabWordMapper.selectByPrimaryKey(wordId);
			waitReviewDTO.setWord(tabWord);
			int total = userWordMapper.countForWaitReview(commonIdDTO.getUserId());
			waitReviewDTO.setTotal(total);
			resultDTO.setData(waitReviewDTO);
			resultDTO.setStatus(Consts.SUCCESS);
			return;
		}

		CommonSet commonSet = commonSetMapper.selectByPrimaryKey(commonIdDTO.getCommonId());
		if(commonSet == null){
			resultDTO.setMessage("单词书不存在");
			return;
		}
		UserBook userBook = new UserBook(commonIdDTO.getUserId(),commonIdDTO.getCommonId());
		userBook = userBookMapper.selectOne(userBook);
		if(userBook == null){
			resultDTO.setMessage("当前登录用户和此单词书没有关联，禁止操作");
			return;
		}
		/*String bookIds;
		if(StringUtils.isEmpty(book.getChildIds())){
			bookIds = book.getId().toString();
		}else{
			bookIds = book.getChildIds().substring(1) + book.getId();
		}
		Integer wordId = bookWordMapper.randomGetWaitReviewWordIdByBookIds(bookIds,reviewWordDTO.getUserId());
		 */
		Integer wordId = bookWordMapper.randomGetWaitReviewWordIdByBookId(commonSet);
		if(wordId == null){
			resultDTO.setMessage("所有待复习的单词已全部复习完成");
			return;
		}
		TabWord tabWord = tabWordMapper.selectByPrimaryKey(wordId);
		waitReviewDTO.setWord(tabWord);
		//int total = bookWordMapper.countWaitReviewByBookIds(bookIds,reviewWordDTO.getUserId());
		int total = bookWordMapper.countWaitReviewByBookId(commonSet);
		waitReviewDTO.setTotal(total);
		resultDTO.setData(waitReviewDTO);
		resultDTO.setStatus(Consts.SUCCESS);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void memory(WordMemoryDTO wordMemoryDTO) {
		userWordMapper.memory(wordMemoryDTO);
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
	public void search(String word, ResultDTO<TabWord> resultDTO,Integer userId) {
		ShanBayResult shanBay;
		String result;
		try {
			result = HttpUtils.get(Consts.SHAN_BAY_SEARCH_URL+word,Consts.CHARSET);
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
		TabWord tabWord = saveWord(shanBay);
		if(tabWord == null || tabWord.getId() == null){
			resultDTO.setMessage("保存单词出现异常");
			return;
		}
		producer.sendMessage(Consts.USER_WORD_QUEUE,StringUtils.getGson().toJson(new UserWord(userId,tabWord.getId())));
		resultDTO.setData(tabWord);
		resultDTO.setStatus(Consts.SUCCESS);
	}

	@Override
	public void getWord(String word, ResultDTO<TabWord> resultDTO) {
		List<TabWord> tabWordList = tabWordMapper.select(new TabWord(word));
		if(tabWordList == null || tabWordList.size() < 1){
			return;
		}
		resultDTO.setData(tabWordList.get(0));
		resultDTO.setStatus(Consts.SUCCESS);
	}
}
