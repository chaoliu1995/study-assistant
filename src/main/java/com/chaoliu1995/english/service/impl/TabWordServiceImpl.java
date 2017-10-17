package com.chaoliu1995.english.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaoliu1995.english.base.impl.BaseServiceImpl;
import com.chaoliu1995.english.dao.CnDefinitionMapper;
import com.chaoliu1995.english.dao.EnDefinitionMapper;
import com.chaoliu1995.english.dao.EnDefnAdjMapper;
import com.chaoliu1995.english.dao.EnDefnAdvMapper;
import com.chaoliu1995.english.dao.EnDefnArtMapper;
import com.chaoliu1995.english.dao.EnDefnConjMapper;
import com.chaoliu1995.english.dao.EnDefnInterjMapper;
import com.chaoliu1995.english.dao.EnDefnNMapper;
import com.chaoliu1995.english.dao.EnDefnNumMapper;
import com.chaoliu1995.english.dao.EnDefnPrepMapper;
import com.chaoliu1995.english.dao.EnDefnPronMapper;
import com.chaoliu1995.english.dao.EnDefnVMapper;
import com.chaoliu1995.english.dao.TabPronunciationsMapper;
import com.chaoliu1995.english.dao.TabWordMapper;
import com.chaoliu1995.english.dao.UkAudioAddressesMapper;
import com.chaoliu1995.english.dao.UsAudioAddressesMapper;
import com.chaoliu1995.english.entity.shanbay.CnDefinition;
import com.chaoliu1995.english.entity.shanbay.EnDefinition;
import com.chaoliu1995.english.entity.shanbay.EnDefnAdj;
import com.chaoliu1995.english.entity.shanbay.EnDefnAdv;
import com.chaoliu1995.english.entity.shanbay.EnDefnArt;
import com.chaoliu1995.english.entity.shanbay.EnDefnConj;
import com.chaoliu1995.english.entity.shanbay.EnDefnInterj;
import com.chaoliu1995.english.entity.shanbay.EnDefnN;
import com.chaoliu1995.english.entity.shanbay.EnDefnNum;
import com.chaoliu1995.english.entity.shanbay.EnDefnPrep;
import com.chaoliu1995.english.entity.shanbay.EnDefnPron;
import com.chaoliu1995.english.entity.shanbay.EnDefnV;
import com.chaoliu1995.english.entity.shanbay.TabPronunciations;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.entity.shanbay.UkAudioAddresses;
import com.chaoliu1995.english.entity.shanbay.UsAudioAddresses;
import com.chaoliu1995.english.model.Audio_addresses;
import com.chaoliu1995.english.model.Cn_definition;
import com.chaoliu1995.english.model.En_definition;
import com.chaoliu1995.english.model.En_definitions;
import com.chaoliu1995.english.model.ShanBayResult;
import com.chaoliu1995.english.model.Word;
import com.chaoliu1995.english.service.TabWordService;
import com.chaoliu1995.english.util.Constants;
import com.chaoliu1995.english.util.EntityUtils;
import com.chaoliu1995.english.util.FileUtils;
import com.chaoliu1995.english.util.Pager;
import com.chaoliu1995.english.util.StringUtils;

@Service("tabWordService")
public class TabWordServiceImpl extends BaseServiceImpl<TabWord> implements TabWordService {
	
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
	private Environment env;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveWord(ShanBayResult sbr,String savePath) {
		//判断返回信息是否正确
		if(!sbr.getMsg().equals("SUCCESS") && !sbr.getStatus_code().equals("0")){
			return;
		}
		Word word = sbr.getData();
		
		//判断单词是否已经存在
		int count = tabWordMapper.selectCount(new TabWord(word.getContent()));
		if(count > 0){
			return;
		}
		
		//保存单词发音文件
		if(!StringUtils.isEmpty(word.getUk_audio())){
			try {
				FileUtils.downLoadFromUrl(word.getUk_audio(),word.getAudio_name() + ".mp3",env.getProperty("file.audioPath") + Constants.UK_AUDIO_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(!StringUtils.isEmpty(word.getUs_audio())){
			try {
				FileUtils.downLoadFromUrl(word.getUs_audio(),word.getAudio_name() + ".mp3",env.getProperty("file.audioPath") + Constants.US_AUDIO_PATH);
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
	}


	@Override
	public Pager<TabWord> listTabWordForExcel(Integer currentPage,Integer pageSize,Integer recordTotal) {
		Pager<TabWord> pager = new Pager<TabWord>(currentPage,pageSize,recordTotal);
		List<TabWord> wordList = tabWordMapper.listTabWordForPager(pager.getStartNum(), pager.getPageSize(),new TabWord());
		pager.setRecordList(wordList);
		return pager;
	}


	@Override
	public TabWord getTabWordByOperateTotalOrderEsc() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void memory(Integer wordId) {
		// TODO Auto-generated method stub
		
	}
	
}
