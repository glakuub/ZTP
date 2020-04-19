using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadanie0.Models
{
    public class ShoppingListModel
    {
        public int? Id { set; get; }
        public string Name { set; get; } 
        public string Comment { set; get; }
        public List<ProductModel> Items { private set; get; }

        public DateTime Modified { set; get; }

        public ShoppingListModel()
        {
            Items = new List<ProductModel>();
            Modified = DateTime.Now;
        }

        public void AddItem(ProductModel item)
        {
            Items.Add(item);
        }

        public void RemoveProduct(int id)
        {
            Items.RemoveAll(p => p.Id == id);
        }
    }
}
