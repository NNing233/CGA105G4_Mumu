$(document).ready(function() {
	$("#showimg").change(function(){
		$("#previewMultiple").html(""); // 清除預覽
		readURL(this);
		});

	function readURL(input){
		if (input.files && input.files.length >= 0) {
			for(var i = 0; i < input.files.length; i ++){
				var reader = new FileReader();
				reader.onload = function (e) {

					var img = '<img class="rounded-circle mt-5" width="200px" height="200px" src="' + e.target.result + '">';
					$("#previewMultiple").append(img);
				}
				reader.readAsDataURL(input.files[i]);
			}
		}else{
			var noPictures = $("<p>目前沒有圖片</p>");
			$("#previewMultiple").append(noPictures);
		}
	}

});