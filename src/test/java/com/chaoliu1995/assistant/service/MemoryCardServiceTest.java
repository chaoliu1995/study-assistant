package com.chaoliu1995.assistant.service;

import com.chaoliu1995.assistant.AssistantApplicationTests;
import com.chaoliu1995.assistant.dto.AddMemoryCardDTO;
import com.chaoliu1995.assistant.dto.BaseResult;
import com.chaoliu1995.assistant.dto.CommonAddDTO;
import com.chaoliu1995.assistant.util.ConstsTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/16 17:35
 */
public class MemoryCardServiceTest extends AssistantApplicationTests {

    @Autowired
    private MemoryCardService memoryCardService;

    @Test
    public void testAddCardBag(){
        CommonAddDTO commonAddDTO = new CommonAddDTO();
        commonAddDTO.setUserId(ConstsTest.USER_ID);
        commonAddDTO.setCommonName("数据库原理");
        BaseResult result = new BaseResult();
        memoryCardService.addCardBag(commonAddDTO,result);
        Assert.assertTrue(result.getStatus().equals(ConstsTest.SUCCESS));
    }

    @Test
    public void testAdd(){
        AddMemoryCardDTO addMemoryCardDTO = new AddMemoryCardDTO();
        addMemoryCardDTO.setUserId(ConstsTest.USER_ID);
        addMemoryCardDTO.setCardBagId(ConstsTest.CARD_BAG_ID);
        addMemoryCardDTO.setQuestion("怎么正确认识近代中国社会的主要矛盾以及中华民族面对的两大历史任务？");
        addMemoryCardDTO.setAnswer("近代中国社会的主要矛盾是帝国主义和中华民族的矛盾、封建主义和人民大众的矛盾。两对矛盾之间的关系：\n" +
                "    中华民族的两大历史任务是求得民族独立和人民解放、实现国家繁荣富强和人民共同富裕。两大历史任务既相互区别，又相互联系。其区别在于前者是要从根本上推翻中国半殖民地半封建社会的统治秩序，着重解决生产关系问题；后者是要改变近代中国经济、文化和社会地位落后的状况，是要充分发展近代民族工商业，着重解决生产力问题。其联系在于，只有完成第一大任务，才能为第二大任务的完成创造条件。");
        BaseResult result = new BaseResult();
        memoryCardService.add(addMemoryCardDTO,result);
        Assert.assertTrue(result.getStatus().equals(ConstsTest.SUCCESS));
    }
}
