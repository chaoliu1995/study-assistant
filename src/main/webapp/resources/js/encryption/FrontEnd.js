/**
 * 前端加密方法
 */

//随机数
	function s20(){ 
			var data=["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]; 
			var result = "";
			for(var i=0;i<8;i++){	
					r=Math.floor(Math.random()*62); 
					result+=data[r];
				} 	
			
			return result;
	 }
	//加密
	function Encrypt(word){
			
			var ran = s20();
			var key = CryptoJS.enc.Utf8.parse("1234567891234567");  //从服务器接收
			
			var iv = CryptoJS.enc.Utf8.parse("0102030405060708");  //从服务器接收
			
			srcs = CryptoJS.enc.Utf8.parse(ran + word);
			
			var encrypted = CryptoJS.AES.encrypt(srcs, key, 
					
					{ iv: iv, mode:CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7}
			);
			 //encrypted.toString()返回的是base64格式的密文
			var str = Base64.encode(encrypted.toString()); 
			
			return str;
		}
	
	//解密
	function Decrypt(word){ 
		var key = CryptoJS.enc.Utf8.parse("0102030405060708"); 
		var iv = CryptoJS.enc.Utf8.parse('0102030405060708'); 
		var decrypt = CryptoJS.AES.decrypt(srcs, key, 
				{ iv: iv,mode:CryptoJS.mode.CBC}); 
		
		return CryptoJS.enc.Utf8.stringify(encrypted).toString(); 
		
	} 