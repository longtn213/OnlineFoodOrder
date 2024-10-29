import './App.css';
import Navbar from "./component/Navbar/Navbar";
import {CssBaseline, ThemeProvider} from "@mui/material";
import {darkTheme} from "./Theme/DarkTheme";

function App() {
  return (
    <div>
        <ThemeProvider theme={darkTheme}/>
        <CssBaseline/>
      <Navbar/>
    </div>
  );
}

export default App;
