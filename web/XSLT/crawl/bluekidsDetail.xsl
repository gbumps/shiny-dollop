<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : bexinhshopDetail.xsl
    Created on : June 18, 2019, 3:28 PM
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
              <xsl:variable name="container" select="main[@class='padding-top-mobile']/div[@class='container']/div/div" />
              <xsl:variable name="info" select="$container/div[1]/div[2]"/>
              <xsl:variable name="images" select="$container/div[1]/div[1]/div/div/div[@class='col-lg-3 col-lg-pull-9 col-md-12 col-sm-12 col-xs-12']/div/ul"/>
              <xsl:variable name="sale" select="$info/span[@class='product-price  hidden-xs']/del"/>
              <xsl:variable name="price" select="$info/div[@class='product-price  hidden-xs']/span"/>
              <xsl:variable name="price1" select="translate($price,'₫','')"/>
              <xsl:variable name="price2" select="translate($price1,',','')"/>
              <xsl:variable name="link" select="link/@href"/>
<!--              <xsl:variable name="images" select="$container/div[@class='product-gallery ']/div[@class='gallery-thumbs']"/>
              <xsl:variable name="options" select="$container/div[@class='product-info']/div[@class='inner']/form[@class='product-form']/div[@class='productoptions section']/div[1]/select[@class='original-selector']"/>-->

              <product>
                <id> 
                    <xsl:value-of select="substring-after($info/div[@class='brand-code  hidden-xs']/span[@class='code-pro'], 'Mã SP: ')"/>
                </id>
                <name>
                    <xsl:value-of select="$info/div[1]/h1"/>
                </name>
                <sale>
                    <xsl:choose>
                        <xsl:when test="$sale != ''">true</xsl:when>
                        <xsl:otherwise>false</xsl:otherwise>
                    </xsl:choose>
                </sale>
                <price>
                    <xsl:value-of select="number($price2)"/>
                </price>
                <oldprice>
                    <xsl:choose>
                        <xsl:when test="$sale != ''">
                            <xsl:variable name="sale1" select="translate($sale,'₫','')"/>
                            <xsl:variable name="sale2" select="translate($sale1,',','')"/>
                            <xsl:value-of select="number($sale2)"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="number($price2)"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </oldprice>
                <type>
                    <xsl:value-of select="substring-before($info/div[1]/h1,' ')"/>
                </type>
                <link>
                    <xsl:value-of select="$link"/>
                </link>
                <images>
                    <xsl:for-each select="$images/li">
                        <image>
                            <xsl:value-of select="a/img/@src"/>
                        </image>  
                    </xsl:for-each>
                </images>
                <options>
                    <xsl:for-each select="$info/form/div[@class='select clearfix']/select/option">
                        <option>
                            <xsl:value-of select="."/>
                        </option>
                    </xsl:for-each>
                </options>
                <sex>
                    <xsl:choose>
                        <xsl:when test="contains($link,'be-trai-4-14')">true</xsl:when>
                        <xsl:otherwise>false</xsl:otherwise>
                    </xsl:choose>
                </sex>
                <distributor>Bluekids</distributor>
                <description>
                    <xsl:for-each select="$container/div[@class='row']/div/div[@id='']/div/div[@data-platform='haravan']/p">
                        <xsl:value-of select="."/>
                    </xsl:for-each>
                </description>
              </product>
            </xsl:for-each>
        </products>
    </xsl:template>

</xsl:stylesheet>
