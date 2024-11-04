import './App.css';
import Navbar from "./component/Navbar/Navbar";
import {CssBaseline, ThemeProvider} from "@mui/material";
import {darkTheme} from "./Theme/DarkTheme";
import Home from "./component/Home/Home";

function App() {
  return (
    <div>
        <ThemeProvider theme={darkTheme}>
            <CssBaseline/>
            <Navbar/>
            <Home/>
        </ThemeProvider>

    </div>
  );
}

export default App;
