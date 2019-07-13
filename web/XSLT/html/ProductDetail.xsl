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
    <xsl:template match="Product"> 
        <xsl:variable name="name" select="Name" />
        <xsl:variable name="price" select="Price" />
        <xsl:variable name="description" select="Description" />
        <xsl:variable name="distributor" select="Distributor" />
        <xsl:variable name="link" select="Link" />
        <xsl:variable name="oldprice" select="OldPrice" />
        <xsl:variable name="sale" select="Sale" />
        <div class="product-detail-wrapper">
            <div class="slideshow-container">
                <xsl:for-each select="Images/ImageLink">
                    <div class="product-detail-img fade">
                        <xsl:variable name="image" select="." />
                        <img src="{$image}" height="500" width="327" /> 
                    </div>
                </xsl:for-each>
                <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                <a class="next" onclick="plusSlides(1)">&#10095;</a>
            </div> 
            <div class="product-detail-info">
                <div class="product-info-detail-text">
                    <h1><xsl:value-of select="$name"/></h1>
                    <h2><xsl:value-of select="$distributor"/></h2>
                    <p><xsl:value-of select="$description"/></p>
                </div>
                <div class="product-detail-price-btn">
                    <xsl:choose> 
                        <xsl:when test="$sale = 1">
                            <del style="display: inline-block;padding-left:38px;">
                                <xsl:value-of select="$oldprice" />
                            </del>
                            VNĐ
                        </xsl:when>
                        <xsl:otherwise> 

                        </xsl:otherwise>
                    </xsl:choose>
                    <p><span><xsl:value-of select="$price"/></span> VNĐ</p>
                    <button type="button" onclick="javascript:location.href='{$link}'">Đi tới sản phẩm</button>
                </div>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
