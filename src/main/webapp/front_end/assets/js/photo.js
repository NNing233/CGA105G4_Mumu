 
        function showPreview() {
            let nameElement = document.getElementById("preview-image");
            let name = nameElement.value;
            let upload = document.getElementById("upload");
            previewImage.src = URL.createObjectURL(upload.files[0]);
        }
 