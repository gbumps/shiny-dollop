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
        <xsl:for-each select="//Product">
            <xsl:variable name="Price" select="Price"/>
            <xsl:variable name="ID" select="ID"/>
            <DIV class="productItem">
                <DIV class="productImageSlide-container">
                    <xsl:for-each select="Images/ImageLink">
                        <DIV class="mySlides fade {$ID}">    
                            <xsl:variable name="image" select="."/>
                            <IMG src="{$image}" style="width:170px; height:200px"/>
                        </DIV>
                    </xsl:for-each>
                    <A class="prev" onclick="plusSlides(-1,{$ID})">&#10094;</A>
                    <A class="next" onclick="plusSlides(1,{$ID})">&#10095;</A>
                </DIV>
                <BR/>
                <DIV style="text-align:center">
<!--                    <xsl:param name="index" select="1" />
                    <xsl:param name="total" select="count(//Images/ImageLink)" />
                    <xsl:if test="not($index = $total)">
                        <xsl:call-template name="repeatable">
                            <xsl:with-param name="index" select="$index + 1"/>
                            <SPAN class="dot" onclick="currentSlide({$index})"/>
                        </xsl:call-template>
                    </xsl:if>-->
                </DIV>
                <DIV class="productInfo">
                    <DIV class="Name">
                        <xsl:value-of select="Name"/>
                    </DIV>
                    <DIV class="Price">
                        <xsl:value-of select="Price"/>
                    </DIV>
                    <DIV class="OldPrice">
                        <xsl:variable name="OldPrice" select="OldPrice"/>
                        <xsl:choose>
                            <xsl:when test="$OldPrice != $Price">
                                <del>
                                    <xsl:value-of select="$OldPrice"/>
                                </del>
                            </xsl:when>
                            <xsl:otherwise>
                            
                            </xsl:otherwise>
                        </xsl:choose>
                    </DIV>
                </DIV>
            </DIV>
        </xsl:for-each> 
    </xsl:template>
</xsl:stylesheet>
