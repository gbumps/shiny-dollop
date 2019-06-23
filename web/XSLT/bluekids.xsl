<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : bexinhshop.xsl
    Created on : June 17, 2019, 1:50 PM
    Author     : stephen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <ProductDetailLinks>
            <xsl:for-each select="//div[@class='item product-item product-grid-item h-hover-alt col-md-3 col-sm-6 col-xs-6 product-wrapper product-item animated zoomIn']">
                <xsl:variable name="link" select="./div/div/div/a/@href"/>
                <Link>https://bluekids.vn<xsl:value-of select="$link"/></Link>
            </xsl:for-each>
        </ProductDetailLinks>
    </xsl:template>

</xsl:stylesheet>
