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
            <DIV class="product-item">
                <DIV class="image-slide-container">
                    <DIV class="mySlides fade {$ID}">    
                        <xsl:variable name="image" select="Images"/>
                        <IMG src="{$image}" style="width:185px; height:220px"/>
                    </DIV>
                </DIV>
                <DIV class="product-info">
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
