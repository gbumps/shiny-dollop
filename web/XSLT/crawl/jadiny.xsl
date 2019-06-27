<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : jadiny.xsl
    Created on : June 12, 2019, 8:44 PM
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
            <xsl:for-each select="//div[@class='block product size-medium fixed-ratio']">
                <xsl:variable name="link" select="./div/a/@href"/>
                <Link>https://jadiny.vn<xsl:value-of select="$link"/></Link>
            </xsl:for-each>
        </ProductDetailLinks>
    </xsl:template>

</xsl:stylesheet>
