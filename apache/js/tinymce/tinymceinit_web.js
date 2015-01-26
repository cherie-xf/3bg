   tinymce.init({
        selector: ".editbox",
        //language: "zh_CN",
        relative_urls: false,
        plugins: [
            "advlist autolink lists link image charmap preview anchor",
            "searchreplace visualblocks visualchars code",
            "media save table contextmenu directionality",
            "emoticons paste textcolor"
        ],
        menubar:false,
        toolbar1: "link imgbutton media | undo redo |  bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent",
        toolbar2: "forecolor backcolor | styleselect | table | code ",	    
        content_css:"/css/global.css",
        setup: function(editor) {
            editor.addButton('imgbutton', {
                text: false,
                title: 'insert image',
                icon: 'image',
                onclick: function() {
                    lightbox(editor);
                }
            });
        }
    });   
	
    function lightbox(editor){
   	 $.colorbox({width:"90%", height:"100%", href:"/web/dialog/medialib.html", iframe:true,fixed:true,
   	    onComplete:function(){
   	    	$("#insertHtml").html("");
   	    },
   		onCleanup:function(){
   		 var insertHtml=$("#insertHtml").html();
   		 editor.insertContent(insertHtml);
   	    }, 
   	    onClosed:function(){
   		 
   	    }});
   }  	