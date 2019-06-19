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
<!--              <xsl:variable name="images" select="$container/div[@class='product-gallery ']/div[@class='gallery-thumbs']"/>
              <xsl:variable name="options" select="$container/div[@class='product-info']/div[@class='inner']/form[@class='product-form']/div[@class='productoptions section']/div[1]/select[@class='original-selector']"/>-->

              <product>
                <id> 
                    <xsl:value-of select="substring-after($info/div[@class='brand-code  hidden-xs']/span[@class='code-pro'], 'Mã SP: ')"/>
                </id>
                <name>
                    <xsl:value-of select="$info/div[1]/h1"/>
                </name>
                <price>
                    <xsl:variable name="price" select="$info/div[@class='product-price  hidden-xs']/span/text()"/>
                    <xsl:variable name="price1" select="translate($price,'₫','')"/>
                    <xsl:variable name="price2" select="translate($price1,'.',',')"/>
                    <xsl:value-of select="number($price2)"/>
                </price>
                <sale>
                    <xsl:variable name="sale" select="$info/span[@class='productlabel sale']/del"/>
                    <xsl:choose>
                        <xsl:when test="($sale='')">false</xsl:when>
                        <xsl:otherwise>true</xsl:otherwise>
                    </xsl:choose>
                </sale>
                <type>
                    <xsl:value-of select="substring-before($info/div[1]/h1,' ')"/>
                </type>
                <link>
                    <xsl:value-of select="link/@href"/>
                </link>
                <images>
<!--                    <xsl:for-each select="$images/a">
                        <image>
                            <xsl:value-of select="./div/div/noscript/img/@src"/>
                        </image>  
                    </xsl:for-each>-->
                </images>
                <options>
                    <xsl:for-each select="$info/form/div[@class='select clearfix']/select/option">
                        <option>
                            <xsl:value-of select="."/>
                        </option>
                    </xsl:for-each>
                </options>
                <sex>
                    <xsl:variable name="sexInfo" select="./div[1]/div/div/div/ol/li[3]/a/text()"/>
                    <xsl:choose>
                        <xsl:when test="($sexInfo='Bé trai (4-14)')">true</xsl:when>
                        <xsl:otherwise>false</xsl:otherwise>
                    </xsl:choose>
                </sex>
                <distributor>
                    Bluekids
                </distributor>
                <description>
<!--                    <xsl:value-of select="//div[@class='descriptionunder reading-container']/p"/>-->
                </description>
              </product>
            </xsl:for-each>
        </products>
    </xsl:template>

</xsl:stylesheet>
