package com.chaoliu1995.assistant.util;

import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.entity.shanbay.TabPronunciations;
import com.chaoliu1995.assistant.entity.shanbay.TabWord;
import com.chaoliu1995.assistant.model.Cn_definition;
import com.chaoliu1995.assistant.model.En_definition;
import com.chaoliu1995.assistant.model.Pronunciations;
import com.chaoliu1995.assistant.model.Word;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/16 18:29
 */
public class EntityUtils {

    private EntityUtils(){}

    /**
     * 校验数据是否存在以及是否是当前用户创建
     * @param obj
     * @param userId
     * @param result
     * @return
     */
    public static final <T extends AbstractUser> boolean identityConfirm(T obj, Integer userId, BaseResult result,String entityType){
        if (obj == null){
            result.setMessage(entityType+"不存在");
            return true;
        }
        if (obj.getUserId() - userId != 0){
            result.setMessage("此"+entityType+"不是您创建的");
            return true;
        }
        return false;
    }

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
        En_definition enDef = word.getEn_definition();
        if(enDef != null){
            tabWord.setEnDefinition(enDef.getDefn());
        }
        Cn_definition cnDef = word.getCn_definition();
        if(cnDef != null){
            tabWord.setCnDefinition(cnDef.getDefn());
        }
        tabWord.setContentId(word.getContent_id());
        if(word.isHas_audio()){
            tabWord.setHasAudio(1);
        }else{
            tabWord.setHasAudio(0);
        }
        tabWord.setContent(word.getContent());
        Pronunciations pron = word.getPronunciations();
        if(pron != null){
            tabWord.setUkPronunciation(pron.getUk());
            tabWord.setUsPronunciation(pron.getUs());
        }
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
