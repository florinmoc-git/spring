import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import {MirthChannels} from '../components/mirth/MirthChannels'
import StickyNote2Icon from '@mui/icons-material/StickyNote2';
import StorageIcon from '@mui/icons-material/Storage';
import { useState } from 'react';
import MirthMessages from '../components/mirth/MirthMessages';

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography component={'span'}>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

export function Mirth() {
  const [value, setValue] = useState(0);
  const [channelId, setChannelId] = useState("")
  const [messagesTabDisabled, setMessagesTabDisabled] = useState(true)
  const [messageStatus, setMessageStatus] = useState()

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const handleShowMessages = (id, status) => {
    setValue(1);
    setChannelId(id);
    setMessageStatus(status);
    setMessagesTabDisabled(false);
  }

  return (
    <Box sx={{ width: '100%', marginLeft: '10px' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={value} onChange={handleChange} aria-label="mirth">
          <Tab icon={<StorageIcon/>} iconPosition='start' label="Channels" {...a11yProps(0)} />
          <Tab disabled={messagesTabDisabled} icon={<StickyNote2Icon/>} iconPosition='start' label="Messages" {...a11yProps(1)} />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <MirthChannels handleShowMessages={handleShowMessages}/>
      </TabPanel>
      <TabPanel value={value} index={1}>
        <MirthMessages channelId={channelId} messageStatus={messageStatus}/>
      </TabPanel>
    </Box>
  );
}
