-- user
INSERT INTO `english_test`.`user`(`id`, `username`, `password`, `reviewing_book_id`, `adding_book_id`)
VALUES (1, 'admin123', 'lAUhpkYbF8aMuCWm8/EwQnUg2EIeN1Ipgik9JNxShuFfgn4LQXhvLXeLnc16HiKeRY7z7wo2Njj99gCTsfb5tw==', NULL, NULL);
-- book
INSERT INTO `english_test`.`book`(`id`, `name`, `user_id`, `child_ids`)
VALUES (1, '初一', 1, NULL);
INSERT INTO `english_test`.`book`(`id`, `name`, `user_id`, `child_ids`)
VALUES (2, '初二', 1, NULL);
-- word
INSERT INTO `english_test`.`word`(`id`, `uk_audio`, `us_audio`, `audio_name`, `content_type`, `cn_definition`, `en_definition`, `content_id`, `has_audio`, `content`, `us_pronunciation`, `uk_pronunciation`)
VALUES (31, 'http://media.shanbay.com/audio/uk/umbrella.mp3', 'http://media.shanbay.com/audio/us/umbrella.mp3', 'umbrella_v4', 'vocabulary', ' n. 伞,保护伞\nvt. 用伞遮住', 'covering or applying simultaneously to a number of similar items or elements or groups', 6313, 1, 'umbrella', 'ʌm\'brelə', 'ʌm\'brelə');
INSERT INTO `english_test`.`word`(`id`, `uk_audio`, `us_audio`, `audio_name`, `content_type`, `cn_definition`, `en_definition`, `content_id`, `has_audio`, `content`, `us_pronunciation`, `uk_pronunciation`)
VALUES (182, 'http://media.shanbay.com/audio/uk/hello.mp3', 'http://media.shanbay.com/audio/us/hello.mp3', 'hello_v3', 'vocabulary', 'int.（见面打招呼或打电话用语）喂,哈罗', 'an expression of greeting', 3130, 1, 'hello', 'hə\'loʊ', 'hə\'ləʊ');
-- book_word
INSERT INTO `english_test`.`book_word`(`id`, `book_id`, `word_id`) VALUES (2, 1, 31);
INSERT INTO `english_test`.`book_word`(`id`, `book_id`, `word_id`) VALUES (1, 1, 182);
-- user_word
INSERT INTO `english_test`.`user_word`(`id`, `user_id`, `word_id`, `search_count`, `show_time`) VALUES (1, 1, 31, 1, 1544153459);
INSERT INTO `english_test`.`user_word`(`id`, `user_id`, `word_id`, `search_count`, `show_time`) VALUES (2, 1, 182, 1, 1544153459);
-- user_book
INSERT INTO `english_test`.`user_book`(`id`, `user_id`, `book_id`, `status`) VALUES (1, 1, 1, 1);

