<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="*" name="two">

    <fo:block>
        <xsl:for-each select=".//propose/ratingBasis">
            <xsl:value-of select="."/>
            <xsl:if test="position() != last()">
                <xsl:text>, </xsl:text>
            </xsl:if>
        </xsl:for-each>
    </fo:block>

</xsl:template>
</xsl:stylesheet>