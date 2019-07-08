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
            <xsl:variable name="id" select="ID" />
            <xsl:variable name="name" select="Name" />
            <xsl:variable name="image" select="Images" />
            <xsl:variable name="price" select="Price" />
            <xsl:variable name="type" select="Type" />
            <div class="product-item">
                <xsl:choose> 
                    <xsl:when test="$image != ''">
                        <div class="product-item-image" style="background-image:url('{$image}');">                                         
                        </div>
                        <a href="ProductDetailServlet?id={$id}&amp;type={$type}" class="product-link">
                           <div class="product-item-image-hover" style="background-image:url('{$image}')"></div>
                        </a>
                    </xsl:when> 
                    <xsl:otherwise>
                        <div class="product-item-image" style="background-image:url('https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg');">                                         
                        </div>
                        <a href="ProductDetailServlet?id={$id}&amp;type={$type}" class="product-link">
                           <div class="product-item-image-hover" style="background-image:url('https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg')"></div>
                        </a>
                    </xsl:otherwise>
                </xsl:choose>
                <div class="product-item-info">
                    <h3 class="product-title">
                        <xsl:value-of select="$name" />
                    </h3>
                    <span class="product-medium-price">
                        <xsl:value-of select="$price" />
                    </span>
                </div>
            </div>
        </xsl:for-each> 
    </xsl:template>
</xsl:stylesheet>
