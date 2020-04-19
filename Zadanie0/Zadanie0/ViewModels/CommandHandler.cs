using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace Zadanie0.ViewModels
{
    class CommandHandler: ICommand
    {
        public event EventHandler CanExecuteChanged;

        private Action _action;
        private Action<object> _actionParam;
        private Predicate<object> _canExecute;

        public CommandHandler(Action action, Predicate<object> _canExecute = null)
        {
            this._action = action;
            this._canExecute = _canExecute;
        }
        public CommandHandler(Action<object> action, Predicate<object> _canExecute = null)
        {
            this._actionParam = action;
            this._canExecute = _canExecute;
        }
        public bool CanExecute(object parameter)
        {
            return _canExecute == null ? true : _canExecute(parameter);
        }

        public void Execute(object parameter)
        {
            if (_actionParam != null)
                this._actionParam(parameter);
            else
                this._action();
        }
        public void OnCanExecuteChanged()
        {
            CanExecuteChanged(this, EventArgs.Empty);
        }
    
}
}
