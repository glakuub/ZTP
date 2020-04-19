using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadanie0.Models
{
    public class ProductModel
    {
        public int? Id { set; get; }
        public int? ShoppingListId { set; get; }
        public string Name { set;get;}
        public string Quantity { set; get; }
        public string Comment { set; get; }
        public DateTime Modified { set; get; }

        public ProductModel()
        {
            Modified = DateTime.Now;
        }


    }
}
