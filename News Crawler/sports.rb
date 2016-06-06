require 'rubygems'
require 'mechanize'

require './single_file'

# mechanize
mechanize = Mechanize.new
p ("connecting using mechanize")
page = mechanize.get('http://www.youm7.com/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1-%D8%A7%D9%84%D8%B1%D9%8A%D8%A7%D8%B6%D8%A9-298')

# sports html file
p ("creating the file html")
outfile = File.new("sports.html","w+")
nextfile = File.new("2.html","w+")
lastfile = File.new("3.html","w+")
x ='<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>AMD News</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- for fontawesome icon css file -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <!-- for content animate css file -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- google fonts  -->
    <link href="http://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Varela" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">   
    <!-- for news ticker css file -->
     <link href="css/li-scroller.css" rel="stylesheet">
     <!-- slick slider css file -->
    <link href="css/slick.css" rel="stylesheet">
    <!-- for fancybox slider -->
     <link href="css/jquery.fancybox.css" rel="stylesheet">    
    <!-- website theme file -->
     <link href="css/theme.css" rel="stylesheet">
    <!-- main site css file -->    
    <link href="style.css" rel="stylesheet">
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
              <a href="#"><img src="img/addbanner_728x90_V1.jpg" alt="img"></a>
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
            <li class="active"><a href="home.html"><span class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
            <li><a href="#">سياسة</a></li>            
            <li><a href="sports.html">رياضة</a></li>
            <li><a href="economics1.html">اقتصاد</a></li> 
            <li><a href="about.html">من نحن</a></li>
            <li><a href="contact.html">اتصل بنا</a></li>
          </ul>           
        </div><!--/.nav-collapse -->      
      </nav>
    </section><!-- End nav section -->
	'
outfile.puts(x)
nextfile.puts(x)
lastfile.puts(x)

news_num = 1
p ("searching for links")
links = page.search "//div[@class='newsBriefBlock']/div[@class='newsListContentData']/h3/a"
  

  left = true
  links.each do |l|
    
    puts ("getting news link from YOUM_7")
    begin
        
        result_page = Mechanize::Page::Link.new( l, mechanize, page ).click
        
        title = result_page.at("//div[@class='newsStoryHeader']/h1").text # Print the page title
	title = title.tr('"', "'")

        image = result_page.at("//div[@class='newsStoryImg']/img") 

	
        date = result_page.at("//p[@class='newsStoryDate']").text 

        details = result_page.at("//div[@class='newsStoryTxt']").text
	
	# creating a single file for each news
	news = SingleFile.new(title, date, image, details)
	news.build
	# ending the file of each news	 
  	file = title.tr('/', '')
	#latest news of the main file => only 4 
	if news_num == 1
	   x ='<section id="newsSection">
	      <div class="row">
		<div class="col-lg-12 col-md-12">
		   <!-- start news sticker -->
		  <div class="latest_newsarea">      
		    <span>اخر اﻻخبار</span>
		    <ul id="ticker01" class="news_sticker">'
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	end
	if news_num <= 5
	   x ='<li><a href="./sports/'+"#{file}"+'.html"><img src="img/news_thumbnail3.jpg" alt="">' + "#{title}" + '</a></li> '
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	   news_num += 1
	end
	if news_num == 6
	   x ='</ul>
		    <div class="social_area">
		      <ul class="social_nav">
		        <li class="facebook"><a href="#"></a></li>
		        <li class="twitter"><a href="#"></a></li>
		        <li class="googleplus"><a href="#"></a></li>
		        <li class="youtube"><a href="#"></a></li>
		        <li class="mail"><a href="mailto:info@smartnews.com"></a></li>
		      </ul>
		    </div>      
		  </div><!-- End news sticker -->
		</div>
	      </div>
	    </section>
	
	    <!-- start slider section -->
	    <section id="sliderSection">
	      <div class="row">
		<div class="col-lg-8 col-md-8 col-sm-8">
		  <div class="slick_slider">'
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	end
	# slider news  => only 4
	if news_num >5 and news_num <=9
	   x = '<div class="single_iteam">
              <a href="./sports/'+file+'.html">' + "#{image}" +'</a>
              <div class="slider_article">
                <h2><a class="slider_tittle" href="./sports/'+file+'.html">' + "#{title}" + '</a></h2>
                <p>' + "#{details.split.first(40).join " "}" + '</p>
              </div>
            </div>'
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	   news_num += 1
	end
	if news_num ==10
	   x ='</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4">
			  <div class="latest_post">
			    <h2><span>اهم اﻻخبار</span></h2>
			    <div class="latest_post_container">
			      <div id="prev-button"><i class="fa fa-chevron-up"></i></div>
			      <ul class="latest_postnav">

		'
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	end
	# important news => only 5
	if news_num >9 and news_num <= 14
	   x = '<li>
		  <div class="media">
                    <a href="./sports/'+file+'.html" class="media-left">' + "#{image}" + '</a>
                    <div class="media-body">
                      <a href="./sports/'+file+'.html" class="catg_title">' + "#{title}" + '</a>                        
                    </div>
                  </div>
                </li>'
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	   news_num +=1
	end
	if news_num == 15
	   x = '</ul>
		     <div id="next-button"><i class="fa  fa-chevron-down"></i></div>
		    </div>
		  </div>
		</div>
	      </div>
	    </section><!-- End slider section -->
		<!-- ==================start content body section=============== -->
	<section id="contentSection">
	      <div class="row">
		<div class="col-lg-8 col-md-8 col-sm-8">
		  <div class="left_content">
		    <div class="single_post_content">
		  <h2><span>اﻻخبار</span></h2>
		</div>'
	   outfile.puts(x)
	   nextfile.puts(x)
	   lastfile.puts(x)
	end
	# the body => rest
	if news_num >14
		if left
		   outfile.puts('<!-- start 2 style category design -->
		    <div class="fashion_technology_area">
		      <div class="fashion">
		        <div class="single_post_content">
		          <ul class="business_catgnav wow fadeInDown">
		            <li>
		              <figure class="bsbig_fig">
		                <a href="./sports/'+file+'.html" class="featured_img"> '+"#{image}"+'
				<span class="overlay"></span>
		                </a>
		                <figcaption>
		                  <a href="./sports/'+file+'.html">'+"#{title}"+'</a>
		                </figcaption><p dir="RTL">'+"#{details.split.first(50).join " "}"+'</p>
		              </figure>
		            </li>
		          </ul>
		        </div>
		      </div>')
		   left = false
		else
		   outfile.puts('<div class="technology">
		        <div class="single_post_content">
		          <ul class="business_catgnav">
		            <li>
		              <figure class="bsbig_fig wow fadeInDown">
		                <a href="./sports/'+file+'.html" class="featured_img">'+"#{image}"+'<span class="overlay"></span>
		                </a>
		                <figcaption>
		                  <a href="./sports/'+file+'.html">'+"#{title}"+'</a>
		                </figcaption>
		                  <p dir="RTL">'+"#{details.split.first(50).join " "}"+'</p>
		              </figure>
		            </li>
		          </ul>
		        </div>
		      </div>
		    </div>
		    ')
		   left = true
		end
	    news_num +=1
        end # end if 

    rescue Mechanize::ResponseCodeError => exception
	puts("There is an error in the link of the news!!!!")
    end # end rescue

  end #end do each
#============================================================================================================
 page = mechanize.get('http://sport.elwatannews.com/ar/1/1/')
	  links = page.search "//div[@class='club_news_related']/h4/a"
  	  left = true
	  links.each do |l|
	    
	    puts ("getting news link from ELWATAN")
	    result_page = Mechanize::Page::Link.new( l, mechanize, page ).click
	    
	    title = result_page.at("//div[@class='one_news_content']/span/h2").text # Print the page title
	    title = title.tr('"', "'")
	    image = result_page.at("//div[@class='one_news_content']/img") 

	    date = result_page.at("//div[@class='today_attr']/h5[@class='today_attr2']").text 

	    details = result_page.at("//div[@class='p_content_p']").text
	    details, *rest = details.split('<')
	    news = SingleFile.new(title, date, image, details)
	    news.build
	    # ending the file of each news	 
  	    file = title.tr('/', '')
	    if left
		   nextfile.puts('<!-- start 2 style category design -->
		    <div class="fashion_technology_area">
		      <div class="fashion">
		        <div class="single_post_content">
		          <ul class="business_catgnav wow fadeInDown">
		            <li>
		              <figure class="bsbig_fig">
		                <a href="./sports/'+file+'.html" class="featured_img"> '+"#{image}"+'
				<span class="overlay"></span>
		                </a>
		                <figcaption>
		                  <a href="./sports/'+file+'.html">'+"#{title}"+'</a>
		                </figcaption><p dir="RTL">'+"#{details.split.first(50).join " "}"+'</p>
		              </figure>
		            </li>
		          </ul>
		        </div>
		      </div>')
		   left = false
	     else
		   nextfile.puts('<div class="technology">
		        <div class="single_post_content">
		          <ul class="business_catgnav">
		            <li>
		              <figure class="bsbig_fig wow fadeInDown">
		                <a href="./sports/'+file+'.html" class="featured_img">'+"#{image}"+'<span class="overlay"></span>
		                </a>
		                <figcaption>
		                  <a href="./sports/'+file+'.html">'+"#{title}"+'</a>
		                </figcaption>
		                  <p dir="RTL">'+"#{details.split.first(50).join " "}"+'</p>
		              </figure>
		            </li>
		          </ul>
		        </div>
		      </div>
		    </div>
		    ')
		   left = true
	    end #end else if
	

	  end #end do each
#===========================================================================================
page = mechanize.get('http://sport.ahram.org.eg/PortalIndex/1/0.aspx')
	  links = page.search "//div[@class='hd_blocks_grn']/a"
  	  left = true
	  links.each do |l|
	    
	    puts ("getting news link from ALAHRAM")
	    result_page = Mechanize::Page::Link.new( l, mechanize, page ).click
	    
	    title = result_page.at("//div[@id='ContentPlaceHolder1_HeaderTitle']/h1").text # Print the page title
	    title = title.tr('"', "'")
	    image = result_page.at("//div[@id='ContentPlaceHolder1_divMediaMain']/div[@class='img_padding']/img") 

	    date = result_page.at("//div[@id='ContentPlaceHolder1_divDate']").text 

	    details = result_page.at("//div[@id='ContentPlaceHolder1_divBody']").text
	    details, *rest = details.split('var')
	    news = SingleFile.new(title, date, image, details)
	    news.build
	    # ending the file of each news	 
  	    file = title.tr('/', '')
	    if left
		   lastfile.puts('<!-- start 2 style category design -->
		    <div class="fashion_technology_area">
		      <div class="fashion">
		        <div class="single_post_content">
		          <ul class="business_catgnav wow fadeInDown">
		            <li>
		              <figure class="bsbig_fig">
		                <a href="./sports/'+file+'.html" class="featured_img"> '+"#{image}"+'
				<span class="overlay"></span>
		                </a>
		                <figcaption>
		                  <a href="./sports/'+file+'.html">'+"#{title}"+'</a>
		                </figcaption><p dir="RTL">'+"#{details.split.first(50).join " "}"+'</p>
		              </figure>
		            </li>
		          </ul>
		        </div>
		      </div>')
		   left = false
	     else
		   lastfile.puts('<div class="technology">
		        <div class="single_post_content">
		          <ul class="business_catgnav">
		            <li>
		              <figure class="bsbig_fig wow fadeInDown">
		                <a href="./sports/'+file+'.html" class="featured_img">'+"#{image}"+'<span class="overlay"></span>
		                </a>
		                <figcaption>
		                  <a href="./sports/'+file+'.html">'+"#{title}"+'</a>
		                </figcaption>
		                  <p dir="RTL">'+"#{details.split.first(50).join " "}"+'</p>
		              </figure>
		            </li>
		          </ul>
		        </div>
		      </div>
		    </div>
		    ')
		   left = true
	    end #end else if
	

	  end #end do each
#====================================================================================================
  x ='<div class="postnav">
	      <ul>
		<li><a href="spots.html">1</a></li>
		<li><a href="2.html">2</a></li>
		<li><a href="3.html">3</a></li>
		<li><a href="#">&raquo;</a></li>
	      </ul>
	      <div class="break"></div>
	    </div>
            </div>
            <!-- End 2 style category design -->          
          </div>
	<div class="col-lg-4 col-md-4 col-sm-4">
          <aside class="right_content">
            <!-- start tab section -->
            <div class="single_sidebar">
               <!-- Nav tabs -->
              <ul class="nav nav-tabs" role="tablist">
                <li role="presentation"><a href="#video" aria-controls="profile" role="tab" data-toggle="tab">فيديوهات</a></li>
                <li role="presentation"><a href="#images" aria-controls="messages" role="tab" data-toggle="tab">صور</a></li>               
              </ul>
              <div class="tab-content">
                <div role="tabpanel" class="tab-pane" id="video">
                  <div class="vide_area">                   
                    <iframe  width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
		 <iframe width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                  </div>
		  <div class="vide_area">                   
                    <iframe style="margin-left:5%" width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
		 <iframe style="margin-left:5%" width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                  </div>
		  <div class="vide_area">                   
                    <iframe  width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
		 <iframe width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                  </div>
		  <div class="vide_area">                   
                    <iframe style="margin-left:5%" width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
		 <iframe style="margin-left:5%" width="95%" height="200" src="http://www.youtube.com/embed/h5QWbURNEpA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                  </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="images">
                  <ul class="spost_nav">
                <li>
                  <div class="media wow fadeInDown">
                    <a href="single_page.html" class="media-left">
                      <img alt="img" src="img/post_img1.jpg">
                    </a>
                    <div class="media-body">
                      <a href="single_page.html" class="catg_title"> صورة رقم 1</a>                        
                    </div>
                  </div>
                </li>
                <li>
                  <div class="media wow fadeInDown">
                    <a href="single_page.html" class="media-left">
                      <img alt="img" src="img/post_img2.jpg">
                    </a>
                    <div class="media-body">
                      <a href="single_page.html" class="catg_title"> صورة رقم 2</a>                        
                    </div>
                  </div>
                </li>
                <li>
                  <div class="media wow fadeInDown">
                    <a href="single_page.html" class="media-left">
                      <img alt="img" src="img/post_img1.jpg">
                    </a>
                    <div class="media-body">
                      <a href="single_page.html" class="catg_title"> صورة رقم 3</a>                        
                    </div>
                  </div>
                </li>
                <li>
                  <div class="media wow fadeInDown">
                    <a href="single_page.html" class="media-left">
                      <img alt="img" src="img/post_img2.jpg">
                    </a>
                    <div class="media-body">
                      <a href="single_page.html" class="catg_title"> صورة رقم 4</a>                       
                    </div>
                  </div>
                </li>
              </ul>
                </div>
              </div>            
            </div>
            <!-- End tab section -->
            
          </aside>
        </div>
  </div>  
    </section> <!-- ==================End content body section=============== -->    
    <footer id="footer">       
             
      <div class="footer_bottom">
        <p class="copyright">
          &copy; حقوق الطبع والنشر محفوظة ل <a href="#" style="color:red">  A  M  D </a> 2015
        </p>
      </div>    
    </footer>
  </div> <!-- /.container -->
  

  <!-- For content animatin  -->
  <script src="js/wow.min.js"></script>
  <!-- bootstrap js file -->
  <script src="js/bootstrap.min.js"></script> 
  <!-- slick slider js file -->
  <script src="js/slick.min.js"></script> 
  <!-- news ticker jquery file -->
  <script src="js/jquery.li-scroller.1.0.js"></script>
  <!-- for news slider -->
  <script src="js/jquery.newsTicker.min.js"></script>
  <!-- for fancybox slider -->
  <script src="js/jquery.fancybox.pack.js"></script>
  <!-- custom js file include -->    
  <script src="js/custom.js"></script> 
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
</html>'
outfile.puts(x)
nextfile.puts(x)
lastfile.puts(x)


puts "Done parsing news"


