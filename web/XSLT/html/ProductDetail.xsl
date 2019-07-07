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
        <xsl:variable name="image" select="ImageLink" />
        <xsl:variable name="price" select="Price" />
        <xsl:variable name="description" select="Description" />
        <xsl:variable name="distributor" select="Distributor" />
        <xsl:variable name="link" select="Link" />
        <div class="product-detail-wrapper">
            <div class="product-detail-img">
                <img src="{$image}" height="500" width="327" /> 
            </div>
            <div class="product-detail-info">
                <div class="product-info-detail-text">
                    <h1><xsl:value-of select="$name"/></h1>
                    <h2><xsl:value-of select="$distributor"/></h2>
                    <p><xsl:value-of select="$description"/></p>
                </div>
                <div class="product-detail-price-btn">
                    <p><span><xsl:value-of select="$price"/></span> VNĐ</p>
                    <button type="button" onclick="javascript:location.href='{$link}'">Đi tới sản phẩm</button>
                </div>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
