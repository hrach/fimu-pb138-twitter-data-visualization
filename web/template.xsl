<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output encoding="utf-8" method="html"/>
    <xsl:template match="documentroot">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                <title>PB138 - Twitter data visualization</title>
                <link rel="stylesheet" type="text/css" href="/pb138-twitter-data-visualization/css/style.css" />
                <link rel="stylesheet" type="text/css" href="/pb138-twitter-data-visualization/css/smoothness/jquery-ui-1.8.20.custom.css" />

                <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
                <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
                <script type="text/javascript" src="/pb138-twitter-data-visualization/js/scripts.js"></script>
            </head>
            <body>
                <div id="container">
                    <div id="header">
                        <h1>PB138 - Twitter data visualization</h1>
                    </div>
                    <div id="content">
                        <div class="topRow">
                            <form id="dateForm" action="index.jsp" method="POST">
                                <xsl:apply-templates select="interval" mode="dateselect" />
                            </form>
                        </div>
                        <div class="leftCol">
                            <form id="trendForm">
                                <xsl:apply-templates select="interval" mode="charthidden" />

                                <div class="block">
                                    <div class="title">
                                        <h2>Most popular trends</h2>
                                    </div>
                                    <ul>
                                        <xsl:if test="trends">
                                            <xsl:apply-templates select="trends/trend" />
                                        </xsl:if>
                                    </ul>
                                </div>
                            </form>
                        </div>
                        <div class="rightCol">
                            <xsl:if test="url">
                                <xsl:apply-templates select="url" />
                            </xsl:if>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="reloadData">
                    <form id="reloadData">
                        <input type="submit" name="reload" value="RELOAD DATA" />
                    </form>
                </div>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="url">
        <xsl:choose>
            <xsl:when test="not(normalize-space(.))">
                Choose some hashtags/trends.
            </xsl:when>
            <xsl:otherwise>
                <xsl:element name="img">
                    <xsl:attribute name="alt">Google chart image</xsl:attribute>
                    <xsl:attribute name="src">
                        <xsl:value-of select="."/>
                    </xsl:attribute>
                </xsl:element>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="trends/trend">
        <li>
            <xsl:element name="input">
                <xsl:attribute name="type">checkbox</xsl:attribute>
                <xsl:attribute name="name">hashtags</xsl:attribute>
                <xsl:attribute name="class">htag</xsl:attribute>
                <xsl:attribute name="value">
                    <xsl:value-of select="."/>
                </xsl:attribute>
                <xsl:if test="@selected='true'">
                    <xsl:attribute name="checked">checked</xsl:attribute>
                </xsl:if>
            </xsl:element>
            <span><xsl:value-of select="."/></span> (<xsl:value-of select="@count"/>)
        </li>
    </xsl:template>
    
    <xsl:template match="interval" mode="charthidden">
        <input type="submit" name="submit" value="CREATE CHART" />
        <xsl:element name="input">
            <xsl:attribute name="type">hidden</xsl:attribute>
            <xsl:attribute name="name">from</xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="from"/>
            </xsl:attribute>
        </xsl:element>
        <xsl:element name="input">
            <xsl:attribute name="type">hidden</xsl:attribute>
            <xsl:attribute name="name">to</xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="to"/>
            </xsl:attribute>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="interval" mode="dateselect">
        <xsl:text>Choose time interval: </xsl:text>
        <xsl:element name="input">
            <xsl:attribute name="type">text</xsl:attribute>
            <xsl:attribute name="name">from</xsl:attribute>
            <xsl:attribute name="id">from</xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="from"/>
            </xsl:attribute>
        </xsl:element>
        <xsl:text> - </xsl:text>
        <xsl:element name="input">
            <xsl:attribute name="type">text</xsl:attribute>
            <xsl:attribute name="name">to</xsl:attribute>
            <xsl:attribute name="id">to</xsl:attribute>
            <xsl:attribute name="value">
                <xsl:value-of select="to"/>
            </xsl:attribute>
        </xsl:element>
        <input type="submit" name="submit" value="FILTER" />
    </xsl:template>
    
</xsl:stylesheet>