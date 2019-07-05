<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : suggestion.xsl
    Created on : July 5, 2019, 10:52 AM
    Author     : stephen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" />

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="Products">
        <xsl:for-each select="//Product">
            <xsl:variable name="ID" select="ID" />
                <div class="product-item">
                    <div class="image-slide-container">
                        <xsl:for-each select="Images/ImageLink">
                            <div class="mySlides fade {$ID}">
                                <xsl:variable name="image" select="." />
                                <img src="{$image}" style="width:185px; height:210px" />
                            </div>
                        </xsl:for-each>
                    </div>
                    <div class="product-item-info">
                        <h3>
                            <xsl:value-of select="Name" />
                        </h3>
                    </div>
                </div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>