<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : jadinyDetail.xsl
    Created on : June 13, 2019, 2:45 PM
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
        <products xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' 
                  xmlns='http://xml.netbeans.org/schema/product'>
        
            <xsl:for-each select="//product">
              <xsl:variable name="container" select="div[@class='product-container cf']" />
              <xsl:variable name="info" select="$container/div[@class='product-info']/div[@class='inner']/div[1]"/>
              <xsl:variable name="images" select="$container/div[@class='product-gallery ']/div[@class='gallery-thumbs']"/>
              <xsl:variable name="options" select="$container/div[@class='product-info']/div[@class='inner']/form[@class='product-form']/div[@class='productoptions section']/div[1]/select[@class='original-selector']"/>

              <product >
                <id> 
                    <xsl:value-of select="$info/div[@class='product-classification']/div/span[@class='sku__value']"/>
                </id>
                <name>
                    <xsl:value-of select="$info/h1[@class='product-title']"/>
                </name>
                <price>
                    <xsl:value-of select="number($info/div[@class='pricearea']/meta[@itemprop='price']/@content)"/>
                </price>
                <sale>
                    <xsl:variable name="sale" select="$info/span[@class='productlabel sale']/span"/>
                    <xsl:choose>
                        <xsl:when test="not($sale='SALE')">false</xsl:when>
                        <xsl:otherwise>true</xsl:otherwise>
                    </xsl:choose>
                </sale>
                <type>
                    <xsl:value-of select="substring-before($info/h1[@class='product-title'],' ')"/>
                </type>
                <link>
                    <xsl:value-of select="link/@href"/>
                </link>
                <images>
                    <xsl:for-each select="$images/a">
                        <image>
                            <xsl:value-of select="./div/div/noscript/img/@src"/>
                        </image>  
                    </xsl:for-each>
                </images>
                <options>
                    <xsl:for-each select="$options/option">
                        <option>
                            <xsl:value-of select="."/>
                        </option>
                    </xsl:for-each>
                </options>
                <distributor>Jadiny</distributor>
                <description>
                    <xsl:value-of select="//div[@class='descriptionunder reading-container']/p"/>
                </description>
              </product>
            </xsl:for-each>
        </products>
    </xsl:template>

</xsl:stylesheet>
