<%@ Page Language="C#" AutoEventWireup="true" CodeFile="CautareNume.aspx.cs" Inherits="Cautare_dupa_nume" MasterPageFile="MasterPage.master" %>

<asp:Content runat="server" ContentPlaceHolderID="ContentPlaceHolder1">
    <form runat="server" method="GET">
        <input type="text" name="search" placeholder="Cautare dupa nume" />
        <button type="submit">Cauta dupa nume</button>
    </form>
    <br />

    <% if (dataArray.Count > 0) { %>
        <p>Utilizatori cu numele sau prenumele "<%= Request.Params["search"] %>"</p>
        <% foreach(Object[] row in dataArray){ %>
            <%= row.GetValue(1) %> <%= row.GetValue(2) %>
            <br />
        <% } %>
    <% } %>

</asp:Content>
