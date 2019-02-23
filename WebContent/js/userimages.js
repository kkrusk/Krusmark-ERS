function encryptUserId(id) {
	$.ajax({ url: 'crypt?s='+id , success: function(data){
		encodeImageUrl(data);
	} });
}

function encodeImageUrl(code) {
	document.getElementById("profilepicture1").src = "./image?u=" + code;
	document.getElementById("profilepicture2").src = "./image?u=" + code;
}