<html>
    <head>
        <title>Minimized Function</title>
        <style>
            body{
                height: 50%;
                background-color: darkgray;
                color: floralwhite;
                vertical-align: middle;
            }
            .allSol{
                text-align: left;
                width: 50%;
            }
            .main{
                background-color: #476e9e;
				width: 90%;
            }
        </style>
    </head>
    <body>
            <a href="index.html">Home Page </a>
            <a href="about.html">| About</a>
            <center>
                <br><br><br><br>
                <div class="main">
                    minimized solution :
                    <h1>
                        <%
                            String[] allMini=(String[])request.getAttribute("allMini");
                            String[] mini=(String[])request.getAttribute("mini");
                            out.print("<p>f = ");
                            out.print(mini[0]);
                            for(int i=1;i<mini.length;i++){
                                out.print(" + " + mini[i]);
                            }
                            out.print("</p></h1><br><br></div> <div class=\"allSol\" >");
                            if (allMini!=null){
                                out.print("<br><u>All possible solutions:<br></u><br>");
                                for(int i=0 ;i < allMini.length; i++){
                                      if(allMini[i]!=null)
                                           out.print("f = " + allMini[i] + "<br>");
                                }
                            }
                           %>
                </div>
            </center>
    </body>
</html>
