-- sys_user
insert into `study_test`.user(id,name,password,email,type,status,create_time)
value(1,'test_user','bBr1vYeMVa0+baERiLTANLVMxd1BCvBnrgWl4Lv2JX3cqgEXioUgLe75B1Q4T1yW','qwr124342@qq.com',1,1,SYSDATE());
-- CommonSet MemoryCard
insert into `study_test`.common_set(id,type_key,user_id,name)
values(1,'101',1,'近代史纲要');
-- CommonSet WordBook
insert into `study_test`.common_set(id,type_key,user_id,name)
values(2,'102',1,'初一');

-- word
INSERT INTO `study_test`.`word`(`id`, `uk_audio`, `us_audio`, `audio_name`, `content_type`, `cn_definition`, `en_definition`, `content_id`, `has_audio`, `content`, `us_pronunciation`, `uk_pronunciation`)
VALUES (31, 'http://media.shanbay.com/audio/uk/umbrella.mp3', 'http://media.shanbay.com/audio/us/umbrella.mp3', 'umbrella_v4', 'vocabulary', ' n. 伞,保护伞\nvt. 用伞遮住', 'covering or applying simultaneously to a number of similar items or elements or groups', 6313, 1, 'umbrella', 'ʌm\'brelə', 'ʌm\'brelə');
INSERT INTO `study_test`.`word`(`id`, `uk_audio`, `us_audio`, `audio_name`, `content_type`, `cn_definition`, `en_definition`, `content_id`, `has_audio`, `content`, `us_pronunciation`, `uk_pronunciation`)
VALUES (182, 'http://media.shanbay.com/audio/uk/hello.mp3', 'http://media.shanbay.com/audio/us/hello.mp3', 'hello_v3', 'vocabulary', 'int.（见面打招呼或打电话用语）喂,哈罗', 'an expression of greeting', 3130, 1, 'hello', 'hə\'loʊ', 'hə\'ləʊ');
-- book_word
INSERT INTO `study_test`.`book_word`(`id`, `book_id`, `word_id`) VALUES (2, 1, 31);
INSERT INTO `study_test`.`book_word`(`id`, `book_id`, `word_id`) VALUES (1, 1, 182);
-- user_word
INSERT INTO `study_test`.`user_word`(`id`, `user_id`, `word_id`, `search_count`, `show_time`) VALUES (1, 1, 31, 1, 1544153459);
INSERT INTO `study_test`.`user_word`(`id`, `user_id`, `word_id`, `search_count`, `show_time`) VALUES (2, 1, 182, 1, 1544153459);
-- user_book
INSERT INTO `study_test`.`user_book`(`id`, `user_id`, `book_id`, `status`) VALUES (1, 1, 1, 1);