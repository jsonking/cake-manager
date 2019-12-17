import React from 'react';
import './App.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import CakeApp from './components/CakeApp';

function App() {
  return (
    <div className="App">
      <AppBar position="static" color="default">
        <Toolbar>
          <Typography variant="h6" color="inherit">
            Cakes
          </Typography>
        </Toolbar>
      </AppBar>
      <CakeApp />
    </div>
  );
}

export default App;
