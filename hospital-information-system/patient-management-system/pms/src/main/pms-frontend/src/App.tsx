import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import BasicAppBar from './components/AppBar';
import Patient from './components/Patient';
import Login from './components/Login';
import Redirect from './components/Redirect';
import Home from './components/Home';

function App() {
  return (
    <div className="App">
      {/* <BasicAppBar />
      <Patient />
      Let's go! */}
      <BrowserRouter>
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/redirect' element={<Redirect />} />
          <Route path='/authorized' element={<Redirect />} />
          <Route path='/home' element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
