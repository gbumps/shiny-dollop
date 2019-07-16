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
                <xsl:variable name="container" select="div[@class='inner-top-50']/div" />
                <xsl:variable name="info" select="$container/div[1]/div[2]/div[@class='product-info']"/>
                <xsl:variable name="images" select="$container/div[1]/div[1]/div[@class='product-image-slider']/section/div/div[1]/ul"/>
                <xsl:variable name="options" select="$info/div[@class='product-attributes ']/div[2]/ul"/>
                <xsl:variable name="price" select="$info/div[@class='product-price']/ins/span[@class='amount']"/>
                <xsl:variable name="oldprice" select="$info/div[@class='product-price']/del/span[@class='amount']"/>
                <xsl:variable name="price1" select="translate($price,' VN&amp;#x110;','')"/>
                <xsl:variable name="price2" select="translate($price1,'.','')"/>
                <xsl:variable name="link" select="link/@href"/>
               
                <product>
                    <id> 
                        <xsl:value-of select="substring-after($info/h2[@class='sku-code']/text(), 'Mã: ')"/>
                    </id>
                    <name>
                        <xsl:value-of select="$container/div[1]/@data-url-slug"/>
                    </name>
                    <sale>
                        <xsl:choose>
                            <xsl:when test="not($oldprice)">
                                false
                            </xsl:when>
                            <xsl:otherwise>
                                true
                            </xsl:otherwise>
                        </xsl:choose>
                    </sale>
                    <price>
                        <xsl:value-of select="number($price2)"/>
                    </price>
                    <oldprice>
                        <xsl:choose>
                            <xsl:when test="not($oldprice)">
                                <xsl:value-of select="number($price2)"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:variable name="sale1" select="translate($oldprice,' VN&amp;#x110;','')"/>
                                <xsl:variable name="sale2" select="translate($sale1,'.','')"/>
                                <xsl:value-of select="number($sale2)"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </oldprice>
                    <type>
                        <xsl:value-of select="substring-before($info/h1[@class='single-product-title'],'-')"/>
                    </type>
                    <link>
                        <xsl:value-of select="$link"/>
                    </link>
                    <images>
                        <xsl:for-each select="$images/li">
                            <image>
                                <xsl:value-of select="./a/@href"/>
                            </image>  
                        </xsl:for-each>
                    </images>
                    <options>
                        <xsl:for-each select="$options/li">
                            <option>
                                <xsl:value-of select="./label/span/text()"/>
                            </option>
                        </xsl:for-each>
                    </options>
                    <sex>
                        <xsl:value-of select="sex" />
                    </sex>
                    <distributor>Jadiny</distributor>
                    <description>Xem chi tiết ở link dưới</description>
                    <cancombine>false</cancombine>
                </product>
            </xsl:for-each>
        </products>
    </xsl:template>

</xsl:stylesheet>
