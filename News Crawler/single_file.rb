class SingleFile
	def initialize(title, date, image, details)
		@title = title
		@image = image
		@date = date
		@details = details
	end


	def build
		file = @title.tr('/', '')
	news_file = File.new("./sports/"+@title.tr('/', '')+".html","w+")
	news_file.puts('<!DOCTYPE html>
	<html>
	  <head>
	    <meta charset="utf-8">
	    <title>AMD News</title>

	    <!-- Bootstrap -->
	    <link href="../css/bootstrap.min.css" rel="stylesheet">
	    <!-- for fontawesome icon css file -->
	    <link href="../css/font-awesome.min.css" rel="stylesheet">
	    <!-- for content animate css file -->
	    <link rel="stylesheet" href="../css/animate.css">
	    <!-- google fonts  -->
	    <link href="http://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
	    <link href="http://fonts.googleapis.com/css?family=Varela" rel="stylesheet" type="text/css">
	    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">   
	    <!-- for news ticker css file -->
	     <link href="../css/li-scroller.css" rel="stylesheet">
	     <!-- slick slider css file -->
	    <link href="../css/slick.css" rel="stylesheet">
	    <!-- for fancybox slider -->
	     <link href="../css/jquery.fancybox.css" rel="stylesheet">    
	    <!-- website theme file -->
	     <link href="../css/theme.css" rel="stylesheet">
	    <!-- main site css file -->    
	    <link href="../style.css" rel="stylesheet">

	    <!-- jQuery Library -->
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
	 
	  </head>
	<body>
	  
	  <div class="container">
	    <!-- start header section -->
	    <header id="header">    
		<div class="col-lg-12 col-md-12 col-sm-12">
		  <div class="header_bottom">
		    <div class="logo_area">
		       <a href="#" class="logo">
		        News <span>Crawler</span>
		      </a> 
		    </div>
		    <div class="add_banner">
		      <a href="#"><img src="../img/addbanner_728x90_V1.jpg" alt="img"></a>
		    </div>
		  </div>
		</div>
	      </div>  
	    </header><!-- End header section --> 
	    <!-- start nav section --> 
	    <section id="navArea">
	      <!-- Start navbar -->
	      <nav class="navbar navbar-inverse" role="navigation">      
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		    <span class="sr-only">Toggle navigation</span>
		    <span class="icon-bar"></span>
		    <span class="icon-bar"></span>
		    <span class="icon-bar"></span>
		  </button>          
		</div>
		<div id="navbar" class="navbar-collapse collapse">
		  <ul class="nav navbar-nav main_nav">
		    <li class="active"><a href="../index.html"><span class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
		    <li><a href="../politics.html">سياسة</a></li>            
		    <li><a href="../sports.html">رياضة</a></li>
		    <li><a href="../arts.html">فن و مشاهير</a></li> 
		    <li><a href="../about.html">من نحن</a></li>
		    <li><a href="../contact.html">اتصل بنا</a></li>
		  </ul>           
		</div><!--/.nav-collapse -->      
	      </nav>
	    </section><!-- End nav section -->
	    <section id="newsSection">
	      <div class="row">
		<div class="col-lg-12 col-md-12">
		   <!-- start news sticker -->
		  <div class="latest_newsarea">      
		    <span>اخر اﻻخبار</span>
		    <ul id="ticker01" class="news_sticker">
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر اﻻول عن كذا</a></li> 
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر التانى عن كذا</a></li>
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر التالت عن كذا</a></li>
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر الرابع عن كذا</a></li> 
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر الخامس عن كذا</a></li>
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر السادس عن كذا</a></li>
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر السابع عن كذا</a></li> 
		      <li><a href="#"><img src="../img/news_thumbnail3.jpg" alt="">الخبر التامن عن كذا</a></li>
		      <li><a href="#"><img src="../img/news_thumbnail2.jpg" alt="">الخبر التاسع عن كذا</a></li>          
		    </ul>
		    <div class="social_area">
		      <ul class="social_nav">
		        <li class="facebook"><a href="#"></a></li>
		        <li class="twitter"><a href="#"></a></li>
		        <li class="googleplus"><a href="#"></a></li>
		        <li class="youtube"><a href="#"></a></li>
		        <li class="mail"><a href="mailto:"></a></li>
		      </ul>
		    </div>      
		  </div><!-- End news sticker -->
		</div>
	      </div>
	    </section>
	     
	    <!-- ==================start content body section=============== -->
	    <section id="contentSection">
	      <div class="row">
		<div class="col-lg-8 col-md-8 col-sm-8">
		  <div class="left_content"><div class="contact_area">
		      <h2 style ="float:center">')
	news_file.puts(@title+'</h2>')
	news_file.puts('<div style ="float:center"><a>' +"#{@image}"+'</a></div>')
	news_file.puts(@date)
	news_file.puts('<p dir="RTL" style="font-size:20px">' + @details +'</p></div>
		    <!-- End about area -->            
		  </div>
		</div>
	       
		<div class="col-lg-4 col-md-4 col-sm-4">
		  <div class="latest_post">
		    <h2><span>اهم اﻻخبار</span></h2>
		    <div class="latest_post_container">
		      <div id="prev-button"><i class="fa fa-chevron-up"></i></div>
		      <ul class="latest_postnav">
		        <li>
		          <div class="media">
		            <a href="#" class="media-left">
		              <img alt="img" src="../img/post_img1.jpg">
		            </a>
		            <div class="media-body">
		              <a href="" class="catg_title"> قبل ايام قلائل من أنطلاق الجولة الثانية من تصفيات الامم الافريقية المؤهلة لنهائيات 2017 في الجابون , بدأ الحديث عن شكل المنافسة</a>                        
		            </div>
		          </div>
		        </li>
		        <li>
		          <div class="media">
		            <a href="#" class="media-left">
		              <img alt="img" src="../img/post_img1.jpg">
		            </a>
		            <div class="media-body">
		              <a href="" class="catg_title"> تتواصل عجلة الدورى العام الممتاز لكرة القدم فى الدوران بنفس سرعة الاسابيع الماضية، لتدخل الليلة فى أسبوعها الـ37 وسط </a>                        
		            </div>
		          </div>
		        </li>
		        <li>
		          <div class="media">
		            <a href="#" class="media-left">
		              <img alt="img" src="../img/post_img1.jpg">
		            </a>
		            <div class="media-body">
		              <a href="" class="catg_title"> Aمع اندلاع الأزمة الراهنة حاليا بين مجلس ادارة النادى الأهلي برئاسة المهندس محمود طاهر مع مجلس ادارة الاتحاد المصري لكرة القدم</a>                        
		            </div>
		          </div>
		        </li>
		        <li>
		          <div class="media">
		            <a href="#" class="media-left">
		              <img alt="img" src="../img/post_img1.jpg">
		            </a>
		            <div class="media-body">
		              <a href="" class="catg_title"> هددت جماهير النادى الأهلي المعروفة بالأولتراس كل المسئولين عن كرة القدم والرياضة في مصر بحرق مبنى الجبلاية واشعال الفوضى</a>                        
		            </div>
		          </div>
		        </li>
		        <li>
		          <div class="media">
		            <a href="#" class="media-left">
		              <img alt="img" src="../img/post_img1.jpg">
		            </a>
		            <div class="media-body">
		              <a href="" class="catg_title"> اعتزل المهاجم عمرو زكي الشهير بـ " البلدوزر " بعد مسيرة كروية كبيرة في مصر أنهائها مع نادي المقاولون العرب ، وفي الدوري الإنجليزي</a>                        
		            </div>
		          </div>
		        </li>
		      </ul>
		     <div id="next-button"><i class="fa  fa-chevron-down"></i></div>
		    </div>
		  </div>
		</div>
	      </div>  
	    </section>
	    <!-- ==================End content body section=============== -->    
	     <footer id="footer">       
		     
	      <div class="footer_bottom">
		<p class="copyright">
		  &copy; حقوق الطبع والنشر محفوظة ل <a href="#" style="color:red">  A  M  D </a> 2015
		</p>
	      </div>    
	    </footer>
	  </div> <!-- /.container -->

	  
	  <!-- For content animatin  -->
	  <script src="../js/wow.min.js"></script>
	  <!-- bootstrap js file -->
	  <script src="../js/bootstrap.min.js"></script> 
	  <!-- slick slider js file -->
	  <script src="../js/slick.min.js"></script> 
	  <!-- news ticker jquery file -->
	  <script src="../js/jquery.li-scroller.1.0.js"></script>
	  <!-- for news slider -->
	  <script src="../js/jquery.newsTicker.min.js"></script>
	  <!-- for fancybox slider -->
	  <script src="../js/jquery.fancybox.pack.js"></script>
	  <!-- custom js file include -->    
	  <script src="../js/custom.js"></script> 
	  <script type="text/javascript">
		$(document).ready(function(){
		$("img").on("error", function() {
	  	$(this).attr("src", "missing.jpg");
		})
		.each(function() {
		    if(this.complete) {
		      $(this).load();
		   } else if(this.error) {
		      $(this).error();
		    }
		});
		});
	  </script>
	  </body>
	</html>')
	end
end
