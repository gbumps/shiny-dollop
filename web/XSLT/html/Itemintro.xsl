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
        <div class="for-boy">
            <h2>Dành cho bé trai</h2>
            <h3>Các loại quần áo phong cách, cá tính mạnh mẽ dành cho bé trai</h3>
            <div class="container-item forboy">
                <xsl:for-each select="//Product">
                    <xsl:variable name="ID" select="ID" />
                    <xsl:variable name="Sex" select="Sex" />
                    <xsl:variable name="Type" select="Type" />
                    <xsl:variable name="image" select="Images"/>
                    <xsl:choose> 
                        <xsl:when test="$Sex = 1">
                            <div class="product-item">
                                <div class="product-item-image" style="background-image:url('{$image}')">                                         
                                </div>
                                <a href="GetProductByTypeServlet?sex={$Sex}&amp;type={$Type}" class="product-link">
                                   <div class="product-item-image-hover" style="background-image:url('{$image}')"></div>
                                </a>
                                <div class="product-item-info">
                                    <h3> <xsl:value-of select="$Type"/> </h3> 
                                    <div class="product-description-option">
                                        
                                    </div>
                                </div>
                            </div>
                        </xsl:when>
                        <xsl:otherwise></xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>        
            </div>
        </div>
        <div class="for-girl">
            <h2>Dành cho bé gái</h2>
            <h3>Các loại váy, đầm đủ kiểu cho các bé gái nhí nhảnh !</h3>
            <div class="container-item forgirl">
                <xsl:for-each select="//Product">
                    <xsl:variable name="ID" select="ID" />
                    <xsl:variable name="Sex" select="Sex" />
                    <xsl:variable name="Type" select="Type" />
                    <xsl:variable name="image" select="Images"/>
                    <xsl:choose> 
                        <xsl:when test="$Sex = 0">
                            <div class="product-item">
                                <div class="product-item-image" style="background-image:url('{$image}')">                                         
                                </div>
                                <a href="GetProductByTypeServlet?sex={$Sex}&amp;type={$Type}" class="product-link">
                                    <div class="product-item-image-hover" style="background-image:url('{$image}')"></div>
                                </a>
                                <div class="product-item-info">
                                    <h3> <xsl:value-of select="$Type"/> </h3> 
                                    <div class="product-description-option">
                                    </div>
                                </div>
                            </div>
                        </xsl:when>
                        <xsl:otherwise></xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
