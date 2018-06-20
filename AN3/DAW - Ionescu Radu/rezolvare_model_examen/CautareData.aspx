<%@ Page Language="C#" AutoEventWireup="true" CodeFile="CautareData.aspx.cs" Inherits="Cautare_dupa_data" MasterPageFile="MasterPage.master" %>

<asp:Content runat="server" ContentPlaceHolderID="ContentPlaceHolder1">
    <form id="form1" runat="server">
    <div>
        <asp:Literal ID="MessagePlaceholder" runat="server"></asp:Literal>
        <h1>Cautare dupa data nasterii</h1>
        <div>
            <asp:TextBox ID="SearchParam" runat="server" Width="79%" style="float: left;"></asp:TextBox>
            <asp:Button ID="Button1" runat="server" OnClick="Search_Click" Width="20%" style="float:left" Text="Cautare" />
        </div>
        
        <br /><br /><br />

        <div>
            <h4>Rezultate cautare</h4>
            <asp:GridView ID="GridView1" runat="server">
            </asp:GridView>
            
        </div>
    </div>
    
    </form>
</asp:Content>