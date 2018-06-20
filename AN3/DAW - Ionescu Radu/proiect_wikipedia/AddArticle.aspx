<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="AddArticle.aspx.cs" Inherits="AddArticle" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">

    <div style="padding: 10px">
        <asp:Label ID="Label1" runat="server" Text="Category:"></asp:Label>
        <br />
        <asp:DropDownList ID="DDLCategory" runat="server" DataSourceID="SqlDataSource1" DataTextField="CategoryName" DataValueField="Id"></asp:DropDownList>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" SelectCommand="SELECT [Id], [CategoryName] FROM [Category]"></asp:SqlDataSource>
    </div>
    <div style="padding: 10px">
        <asp:Label ID="Label2" runat="server" Text="Title:"></asp:Label>
        <br />
        <asp:TextBox ID="TBTitle" runat="server"></asp:TextBox>
        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" ControlToValidate="TBTitle" runat="server" ErrorMessage="Article title is required" ForeColor="Red"></asp:RequiredFieldValidator>
    </div>
    <div style="padding: 10px">
        <asp:Label ID="Label3" runat="server" Text="Text:"></asp:Label>
        <br />
        <asp:TextBox ID="TBText" runat="server"></asp:TextBox>
        <asp:RequiredFieldValidator ID="RequiredFieldValidator2" ControlToValidate="TBText" runat="server" ErrorMessage="Text is required" ForeColor="Red"></asp:RequiredFieldValidator>
    </div>
    <div style="padding: 10px">
        <asp:Label ID="Label4" runat="server" Text="Publication Date:"></asp:Label>
        <br />
        <asp:TextBox ID="TBDate" runat="server"></asp:TextBox>
        <asp:RequiredFieldValidator ID="RequiredFieldValidator3" ControlToValidate="TBDate" runat="server" ErrorMessage="Publication date is required" ForeColor="Red" Display="Dynamic"></asp:RequiredFieldValidator>
        <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToValidate="TBDate" ErrorMessage="Date must be in MM/DD/YYYY format" ForeColor="Red" Type="Date" Operator="DataTypeCheck" Display="Dynamic"></asp:CompareValidator>
    </div>
    <div style="padding: 10px">
        <asp:Button ID="AddButton" runat="server" Text="Add New Article" OnClick="AddButton_Click" />
    </div>
    <div style="padding: 10px">
        <asp:Label ID="LAnswer" runat="server" Text=""></asp:Label>
    </div>
</asp:Content>

