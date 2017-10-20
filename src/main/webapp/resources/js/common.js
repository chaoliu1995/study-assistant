/**
 * 
 */
/**
 * 清除form中的数据
 * @param idStr
 * @returns
 */
function clearForm(idStr){
	$(idStr).form('clear');
}
/**
 * 判断字符串是否为空
 * @param str
 * @returns
 */
function isEmpty(str){
	if(str == null || str == "" || str == undefined || str.length == 0){
		return true;
	}
	return false;
}