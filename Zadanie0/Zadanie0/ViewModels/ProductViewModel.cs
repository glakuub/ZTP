using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.ViewModels
{
    public class ProductViewModel : ViewModelBase<ProductModel>
    {
        private static int INVALID_INDEX = -1;
        public int Id { set { SetProperty(_this.Id, value, () => _this.Id = value); } get { return _this.Id??INVALID_INDEX; } }
        public string Name { set { SetProperty(_this.Name, value, ()=> _this.Name = value); } get { return _this.Name; } }
        public string Quantity { set { SetProperty(_this.Quantity, value, ()=> _this.Quantity = value); } get { return _this.Quantity; } }
        public string Comment { set { SetProperty(_this.Comment, value, ()=> _this.Comment = value); } get { return _this.Comment; } }
        public ProductViewModel(ProductModel productModel = null) : base(productModel)
        {
        }
    }
}
