<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 

<xsl:template match="/">
	<html>
	<title>Database Connection Details</title>
	<body>
		<h2>Database Connection Details</h2>
		<ul>
			<xsl:for-each select="Context/Resource">
				<li>Database Name: <strong><xsl:value-of select="@name"/></strong>
				 	<table border="0" cellpadding="1" width="100%">
						<tr>
							<td width="20">o</td>
							<td width="200">auth: </td>
							<td><xsl:value-of select="@auth"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>driverClassName: </td>
							<td><xsl:value-of select="@driverClassName"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>maxActive: </td>
							<td><xsl:value-of select="@maxActive"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>maxIdle: </td>
							<td><xsl:value-of select="@maxIdle"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>maxWait: </td>
							<td><xsl:value-of select="@maxWait"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>type: </td>
							<td><xsl:value-of select="@type"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>type: </td>
							<td><xsl:value-of select="@url"/></td>
						</tr>
						<tr>
							<td>o</td>
							<td>username: </td>
							<td><xsl:value-of select="@name"/></td>
						</tr>
					</table>
				</li>
			</xsl:for-each>
		</ul>
	</body>
	</html>
</xsl:template>

</xsl:stylesheet>