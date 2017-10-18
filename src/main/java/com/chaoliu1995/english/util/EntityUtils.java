package com.chaoliu1995.english.util;

import com.chaoliu1995.english.entity.shanbay.TabPronunciations;
import com.chaoliu1995.english.entity.shanbay.TabWord;
import com.chaoliu1995.english.model.Pronunciations;
import com.chaoliu1995.english.model.Word;

public class EntityUtils {
	
	/**
	 * 将从扇贝获得的单词数据结构转换为本地数据库实体
	 * @param word
	 * @return
	 */
	public static TabWord wordToTabWord(Word word){
		TabWord tabWord = new TabWord();
		tabWord.setUkAudio(word.getUk_audio());
		tabWord.setAudioName(word.getAudio_name());
		tabWord.setContentType(word.getContent_type());
		tabWord.setDefinition(word.getDefinition());
		tabWord.setContentId(word.getContent_id());
		if(word.isHas_audio()){
			tabWord.setHasAudio(1);
		}else{
			tabWord.setHasAudio(0);
		}
		tabWord.setContent(word.getContent());
		tabWord.setPron(word.getPron());
		tabWord.setPronunciation(word.getPronunciation());
		tabWord.setUsAudio(word.getUs_audio());
		return tabWord;
	}
	
	/**
	 * 将从扇贝获得的单词音标数据结构转换为本地数据库实体
	 * @param pronunciations
	 * @return
	 */
	public static TabPronunciations pronunciationsToTabPronunciations(Pronunciations pronunciations){
		TabPronunciations tp = new TabPronunciations();
		tp.setUk(pronunciations.getUk());
		tp.setUs(pronunciations.getUs());
		return tp;
	}
	
}
