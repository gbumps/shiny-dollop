<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : mainPage.xsl
    Created on : June 27, 2019, 3:47 PM
    Author     : stephen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
         <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="Products">
        <DIV class="for-boy">
            <H2>Dành cho bé trai</H2>
            <H3>Các loại quần áo phong cách, cá tính mạnh mẽ dành cho bé trai</H3>
            <DIV class="container-item-forboy">
                <xsl:for-each select="//Product">
                    <xsl:variable name="ID" select="ID" />
                    <xsl:variable name="Sex" select="Sex" />
                    <xsl:variable name="Type" select="Type" />
                    <xsl:choose> 
                        <xsl:when test="$Sex = 1">
                            <DIV class="product-item-horizontal">
                                <DIV class="slide-container">
                                    <xsl:for-each select="Images/ImageLink">
                                        <DIV class="mySlides fade {$ID}">    
                                            <xsl:variable name="image" select="."/>
                                            <IMG src="{$image}" style="width:185px; height:210px"/>
                                        </DIV>
                                    </xsl:for-each>
                                    <A class="prev" onclick="plusSlides(-1, {$ID})">&#10094;</A>
                                    <A class="next" onclick="plusSlides(1, {$ID})">&#10095;</A>
                                </DIV> 
                                <DIV class="product-item-info">
                                    <H3>
                                        <xsl:if test="$Type = 'Áo'">
                                            Áo
                                        </xsl:if>
                                        <xsl:if test="$Type = 'Quần'">
                                            Quần
                                        </xsl:if>
                                    </H3> 
                                </DIV>
                            </DIV>
                        </xsl:when>
                        <xsl:otherwise></xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>        
            </DIV>
        </DIV>
        <DIV class="for-girl">
            <H2>Dành cho bé gái</H2>
            <H3>Các loại váy, đầm đủ kiểu cho các bé gái nhí nhảnh !</H3>
            <DIV class="container-item-forgirl">
                <xsl:for-each select="//Product">
                    <xsl:variable name="ID" select="ID" />
                    <xsl:variable name="Sex" select="Sex" />
                    <xsl:variable name="Type" select="Type" />
                    <xsl:choose> 
                        <xsl:when test="$Sex = 0">
                            <DIV class="product-item-horizontal">
                                <DIV class="slide-container">
                                    <xsl:for-each select="Images/ImageLink">
                                        <DIV class="mySlides fade {$ID}">    
                                            <xsl:variable name="image" select="."/>
                                            <IMG src="{$image}" style="width:185px; height:210px"/>
                                        </DIV>
                                    </xsl:for-each>
                                    <A class="prev" onclick="plusSlides(-1, {$ID})">&#10094;</A>
                                    <A class="next" onclick="plusSlides(1, {$ID})">&#10095;</A>
                                </DIV> 
                                <DIV class="product-item-info">
                                    <H3>
                                        <xsl:if test="$Type = 'Áo'">
                                            Áo
                                        </xsl:if>
                                        <xsl:if test="$Type = 'Quần'">
                                            Quần
                                        </xsl:if>
                                    </H3> 
                                </DIV>
                            </DIV>
                        </xsl:when>
                        <xsl:otherwise></xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>
            </DIV>
        </DIV>
    </xsl:template>
</xsl:stylesheet>
