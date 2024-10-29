import React from 'react';
import SearchIcon from '@mui/icons-material/Search';
import {Avatar, IconButton} from "@mui/material";
import {pink} from "@mui/material/colors";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
const Navbar = () => {
    return (
        <div className='px-5 z-50 py-[.8rem] bg-[#e91e63] lg:px-20 flex justify-between'>
            <div className='lg:mr-10 cursor-pointer flex items-center space-x-4'>
                <li className='logo font-semibold text-gray-300 text-2xl'> Dragon Ordering Food Online</li>
            </div>
            <div className='flex items-center space-x-2 lg:space-x-10'>
                <div className=''>
                    <IconButton>
                        <SearchIcon sx={{fontSize: "1.5rem"}}/>
                    </IconButton>
                </div>
                <div className=''>
                    <Avatar sx={{bgcolor: "White", color: pink.A400}}>C</Avatar>
                </div>
                <div className=''>
                    <IconButton>
                        <ShoppingCartIcon sx={{fontSize: "1.5rem"}}/>
                    </IconButton>
                </div>
            </div>
        </div>
    );
};

export default Navbar;