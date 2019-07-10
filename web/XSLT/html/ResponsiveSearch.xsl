<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" />

    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="Products">
        <xsl:for-each select="//Product">
            <xsl:variable name="ID" select="ID"/>
            <xsl:variable name="Type" select="Type"/>
            <div class="search-result-element" onclick="javascript:location.href='ProductDetailServlet?id={$productID}&amp;type={$Type}'">
                <p class="search-result-name">
                    <xsl:value-of select="Name" />
                </p>
            </div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
