<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Stergere.aspx.cs" Inherits="Stergere" MasterPageFile="MasterPage.master" %>

<asp:Content runat="server" ContentPlaceHolderID="ContentPlaceHolder1">
    <form id="form1" runat="server">
        <h3> Stergere </h3>
        <br />
        <br />

        <% foreach(Object[] row in dataArray) { %>
            <% if (Request.Params["recenzii"] == null)
               { %>
                <a href="Stergere.aspx?recenzii=<%= row.GetValue(0) %>">Recenzii: <%= row.GetValue(0) %></a> 
            <% } else { %>
                 <a href="Stergere.aspx?id=<%= row.GetValue(0) %>">Stergere: <%= row.GetValue(1) %></a>
            <% } %>
            <br />
        <% } %>
    </form>
</asp:Content>
