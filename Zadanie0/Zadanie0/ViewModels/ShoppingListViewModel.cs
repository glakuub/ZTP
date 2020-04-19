using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Zadanie0.Models;

namespace Zadanie0.ViewModels
{
    public class ShoppingListViewModel: ViewModelBase<ShoppingListModel>
    {
        private static int INVALID_INDEX = -1;
        public int Id { set { SetProperty(_this.Id, value, () => _this.Id = value); } get { return _this.Id??INVALID_INDEX; } }
        public string Name { set { SetProperty(_this.Name, value, () => _this.Name = value); } get { return _this.Name; } }
        public string Comment { set { SetProperty(_this.Comment, value, () => _this.Comment = value); } get { return _this.Comment; } }
        public ObservableCollection<ProductViewModel> Products { set; get; }
        private int _selectedIndex = -1;
        public int SelectedProductIndex { set { SetProperty(ref _selectedIndex, value); } get{ return _selectedIndex; } }
        public ICommand AddProduct { set; get; }
        public ICommand DeleteProduct { set; get; }
        public ShoppingListViewModel(ShoppingListModel shoppingListModel) : base(shoppingListModel)
        {
            Products = new ObservableCollection<ProductViewModel>();
            foreach(var pm in shoppingListModel.Items)
            {
                Products.Add(new ProductViewModel(pm));
            }
            AddProduct = new CommandHandler(() => AddEmptyProduct());
            DeleteProduct = new CommandHandler(() => DeleteSelected());
        }

        private void AddEmptyProduct()
        {
            var p = new ProductModel();
            if (Id != INVALID_INDEX) p.ShoppingListId = Id;
            Model.AddItem(p);
            Products.Add(new ProductViewModel(p));
        }
        private void DeleteSelected()
        {
            if (_selectedIndex > -1 && _selectedIndex < Products.Count)
            {
                var toRemove = Products.ElementAt(_selectedIndex);
                Model.RemoveProduct(toRemove.Id);
                Products.RemoveAt(_selectedIndex);

            }
        }
    }
}
