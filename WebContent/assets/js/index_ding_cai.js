function agree(id) {
	$.post("agreeProduct.do", {
		'id' : id
	}, function(data, textStatus) {

		// data 可以是 xmlDoc, jsonObj, html, text, 等等.
		//this; // 这个Ajax请求的选项配置信息，请参考jQuery.get()说到的this
		if (data == "\"{rs:1}\"")
			$("#agree_" + id).html(parseInt($("#agree_" + id).html()) + 1);
			        else alert("谢谢投票，请勿重复投票")
	}, "html");

}

function disagree(id) {
	$.post("disagreeProduct.do", {
		'id' : id
	}, function(data, textStatus) {
		// data 可以是 xmlDoc, jsonObj, html, text, 等等.
		//this; // 这个Ajax请求的选项配置信息，请参考jQuery.get()说到的this
		if (data == "\"{rs:1}\"")
			$("#disagree_" + id).html(parseInt($("#disagree_" + id).html()) + 1);
        else alert("谢谢投票，请勿重复投票")
	}, "html");
}
