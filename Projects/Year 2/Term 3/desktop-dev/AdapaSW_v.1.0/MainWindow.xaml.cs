using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApp_Prototype_v._2._0
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {        
        public MainWindow()
        {
            InitializeComponent();
            init();
        }

        public void init()
        {
            couchDb.getMyStocks();
            couchDb.getAllStocks();
            lvItems.ItemsSource = couchDb.myStocks;
            CollectionView view = (CollectionView)CollectionViewSource.GetDefaultView(lvItems.ItemsSource);
            view.Filter = SymbolFilter;
        }

        private bool SymbolFilter(object item)  
        {
            if (String.IsNullOrEmpty(txtFilter.Text))
                return true;
            else
                return ((item as StockList.Value).Name.IndexOf(txtFilter.Text, StringComparison.OrdinalIgnoreCase) >= 0);
        }

        private void txtFilter_TextChanged(object sender, TextChangedEventArgs e)
        {
            CollectionViewSource.GetDefaultView(lvItems.ItemsSource).Refresh();
        }

        private void lvItems_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            StockList.Value v = (StockList.Value)lvItems.SelectedItem;
            if (v != null)
            {
                XmlDataProvider provider = (XmlDataProvider)grdRssFeed.FindResource("rssData");

                switch (v.Market)
                { 
                    case "NASDAQ":
                        provider.Source = new Uri("http://articlefeeds.nasdaq.com/nasdaq/symbols?symbol=" + v.Symbol);
                        break;
                    case "LSE":
                        provider.Source = new Uri("http://www.londonstockexchange.com/exchange/CompanyNewsRSS.html?newsSource=RNS&indexSymbol=ASX");
                        break;
                    case "DAX":
                        provider.Source = new Uri("http://feeds.finance.yahoo.com/rss/2.0/headline?s=" + v.Symbol + "&region=US&lang=en-US");
                        break;
                }

                if (!couchDb.isMyStock(v.Symbol))
                {
                    btnDelete.Visibility = System.Windows.Visibility.Collapsed;
                    btnAdd.Visibility = System.Windows.Visibility.Visible;
                }
                else
                {
                    btnDelete.Visibility = System.Windows.Visibility.Visible;
                    btnAdd.Visibility = System.Windows.Visibility.Collapsed;
                }

                wbChart.Navigate(new Uri("http://83.254.83.56:8001/files/highcharts/chart.html?symbol=" + v.Symbol + "&market=" + v.Market.ToLower()));
            }
        }

        private void mnuMyStocks_Click(object sender, RoutedEventArgs e)
        {
            lvItems.ItemsSource = couchDb.myStocks;
            lvItems.SelectedIndex = 0;
            CollectionView view = (CollectionView)CollectionViewSource.GetDefaultView(lvItems.ItemsSource);
            view.Filter = SymbolFilter;
        }

        private void mnuNasdaq_Click(object sender, RoutedEventArgs e)
        {
            lvItems.ItemsSource = couchDb.Nasdaq;
            lvItems.SelectedIndex = 0;
            CollectionView view = (CollectionView)CollectionViewSource.GetDefaultView(lvItems.ItemsSource);
            view.Filter = SymbolFilter;
        }

        private void mnuLse_Click(object sender, RoutedEventArgs e)
        {
            lvItems.ItemsSource = couchDb.Lse;
            lvItems.SelectedIndex = 0;
            CollectionView view = (CollectionView)CollectionViewSource.GetDefaultView(lvItems.ItemsSource);
            view.Filter = SymbolFilter;
        }

        private void mnuDax_Click(object sender, RoutedEventArgs e)
        {
            lvItems.ItemsSource = couchDb.Dax;
            lvItems.SelectedIndex = 0;
            CollectionView view = (CollectionView)CollectionViewSource.GetDefaultView(lvItems.ItemsSource);
            view.Filter = SymbolFilter;
        }

        private void linkGoToArticle_Click(object sender, RoutedEventArgs e)
        {
            string url = (sender as Hyperlink).Tag as String;
            if (String.IsNullOrWhiteSpace(url)) return;

            System.Diagnostics.Process.Start(url);
        }

        private void btnAdd_Click(object sender, RoutedEventArgs e)
        {
            StockList.Value v = (StockList.Value)lvItems.SelectedItem;
            if (v != null) couchDb.addStock(v.Symbol, v.Market.ToLower());
        }

        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            StockList.Value v = (StockList.Value)lvItems.SelectedItem;
            if (v != null) couchDb.deleteStock(v.Symbol);
        }

        private void txtFilter_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            txtFilter.Text = "";
        }
    }
}
