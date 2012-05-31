<%-- 
    Document   : index
    Created on : 1.5.2012, 14:29:30
    Author     : Jan Skrasek <hrach.cz@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>PB138 - Twitter data visualization</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    </head>
    <body>
        <div id="container">
		<div id="header">
			<h1>PB138 - Twitter data visualization</h1>
		</div>
		<div id="content">
			<div class="topRow">
				Choose time interval: <input type="text" name="from" /> - <input type="text" name="to" /> <input type="submit" name="submit" value="SUBMIT" />
			</div>
			<div class="leftCol">
				<div class="block">
					<div class="title">
						<h2>Most popular trends</h2>
					</div>
					<ul>
						<li>
							<input type="checkbox" name="" value="" /> trend#1
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#2
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#3
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#4
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#5
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#6
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#7
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#8
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#9
						</li>
						<li>
							<input type="checkbox" name="" value="" /> trend#10
						</li>
					</ul>
				</div>
				<div class="block">
					<div class="title">
						<h2>Additional trend</h2>
					</div>
					<ul>
						<li class="large">
							<select name="">
								<option value="">trend#11</option>
								<option value="">trend#12</option>
								<option value="">trend#13</option>
							</select>
						</li>
					</ul>
				</div>
				<input type="submit" name="submit" value="CREATE GRAPH" />
			</div>
			<div class="rightCol">
				<img src="https://chart.googleapis.com/chart?cht=lc&chs=480x320&chd=t:60,70,20,80,90|10,20,67,22,57&chxt=x,y&chxl=0:|1|2|3|4|5|1:||20||40||60||80||100&chdl=hastag|twitter&chco=3072F3,ff0000,00aaaa" alt="chart" />
			</div>
			<div class="clear"></div>
		</div>
	</div>
    </body>
</html>
