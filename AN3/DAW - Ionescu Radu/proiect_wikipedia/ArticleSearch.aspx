<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="ArticleSearch.aspx.cs" Inherits="ArticleSearch" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">

    <asp:SqlDataSource ID="SDSSearch" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" 
        SelectCommand="SELECT Article.Id, Article.Title, Article.Text, Article.Date, Category.CategoryName FROM Category INNER JOIN Article ON Category.Id = Article.IdCategory">
    </asp:SqlDataSource>

    <asp:Label ID="LSelect" runat="server" Text=""></asp:Label>
    <br />
    <asp:Repeater ID="Repeater1" runat="server" DataSourceID="SDSSearch">
        <HeaderTemplate>
            Search results:
        </HeaderTemplate>
        <ItemTemplate>
            <div style="padding: 10px; background-color: #d8ddf9">
                <h3><%# DataBinder.Eval(Container.DataItem, "CategoryName")%> <%# DataBinder.Eval(Container.DataItem, "Title")%></h3>
            
                <div>
                    Text: <%# DataBinder.Eval(Container.DataItem, "Text")%>
                </div>
                <div>
                     Publication Date: <%# DataBinder.Eval(Container.DataItem, "Date")%>
                </div>
                 
                <asp:LoginView ID="LoginView1" runat="server">
                    <LoggedInTemplate>
                        <asp:HyperLink ID="HyperLink1" NavigateUrl='<%# "~/UpdateArticle.aspx?id=" + DataBinder.Eval((Container.Parent as RepeaterItem).DataItem, "Id")%>' runat="server" ForeColor="Black" >Update Article</asp:HyperLink>
                    </LoggedInTemplate>
                </asp:LoginView>
             </div>
        </ItemTemplate>
        <SeparatorTemplate>
            <br />
        </SeparatorTemplate>
    </asp:Repeater>
</asp:Content>

