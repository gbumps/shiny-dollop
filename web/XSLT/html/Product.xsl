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
          
<!--              <xsl:variable name="images" select="$container/div[@class='product-gallery ']/div[@class='gallery-thumbs']"/>
              <xsl:variable name="options" select="$container/div[@class='product-info']/div[@class='inner']/form[@class='product-form']/div[@class='productoptions section']/div[1]/select[@class='original-selector']"/>-->
            <xsl:variable name="Price" select="Price"/>
            <DIV class="productItem">
                <DIV class="Name">
                    <xsl:value-of select="Name"/>
                </DIV>
                <DIV class="Price">
                    <xsl:value-of select="Price"/>
                </DIV>
                <DIV class="OldPrice">
                    <xsl:variable name="OldPrice" select="OldPrice"/>
                    <xsl:choose>
                        <xsl:when test="($OldPrice != $Price)">
                            <del>
                                <xsl:value-of select="$OldPrice"/>
                            </del>
                        </xsl:when>
                        <xsl:otherwise>
                            
                        </xsl:otherwise>
                    </xsl:choose>
                </DIV>
            </DIV>
<!--                <DIV class="Name">
                    <xsl:value-of select="number($price2)"/>
                </PRICE>
                <PRICE>
                   
                </PRICE>
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
              </product>-->
        </xsl:for-each> 
    </xsl:template>
</xsl:stylesheet>
