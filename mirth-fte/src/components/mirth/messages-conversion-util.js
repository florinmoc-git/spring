/**
 * Utilities to convert Mirth messages api reponse to display-friendly object.
 * Deals with the fact that mirth response list.message.connectorMessages.entry could be
 * either an object or an array, depending on whether both source and destination connector
 * messages are returned.
 */


const formatDateTimeLong = (dateTime) => {
    return new Date(dateTime)
        .toISOString()
        .replace("T", " ")
        .replace("Z", "");
};

const populateCurrentMessage = (currentMessage, item) => {
    currentMessage.connector = item.connectorMessage.connectorName;
    currentMessage.status = item.connectorMessage.status;
    currentMessage.receivedDate = formatDateTimeLong(
        item.connectorMessage.receivedDate.time
    );
    currentMessage.responseDate = formatDateTimeLong(
        item.connectorMessage.responseDate.time
    );
    currentMessage.source =
        item.connectorMessage.metaDataMap.entry[1].string[1];
    currentMessage.type = item.connectorMessage.metaDataMap.entry[3].string[1];
    currentMessage.rawMessage = item.connectorMessage.raw.content;

    return currentMessage;
};

export const convertMessagesJson = (data) => {    
    const messagesForDisplay = [];
    const messages = data.list.message;

    messages.forEach((message) => {
        let entry = message.connectorMessages.entry;
        if (Array.isArray(entry)) {
            // create one entry for source
            // and one or more entries for destination(s)
            entry.forEach((item) => {
                let currentMessage = {};
                // currentMessage.id is created to give a unique id to each mui DataGrid row
                if (item.connectorMessage.connectorName === "Source") {
                    // message with id
                    currentMessage.id = message.messageId;
                    currentMessage.messageId = message.messageId;
                    currentMessage = populateCurrentMessage(currentMessage, item);
                } else {
                    // message without id
                    currentMessage.id = message.messageId + "_Z";
                    currentMessage.messageId = '';
                    currentMessage = populateCurrentMessage(currentMessage, item);
                    
                }
                messagesForDisplay.push(currentMessage);
            });
        } else {
            if (entry.connectorMessage.connectorName === "Source") {
                // create one entry per message
                let currentMessage = {};
                currentMessage.id = message.messageId;
                currentMessage.messageId = message.messageId;
                currentMessage = populateCurrentMessage(currentMessage, entry);
                messagesForDisplay.push(currentMessage);
            } else {
                // create blank entry for source, with Id
                // create one or more entries for destination(s)
                let currentMessageSource = {};
                currentMessageSource.id = message.messageId;
                currentMessageSource.messageId = message.messageId;
                currentMessageSource.connector = '---';
                currentMessageSource.status = '---';
                currentMessageSource.receivedDate = '---';
                currentMessageSource.responseDate = '---';
                currentMessageSource.source ='---';
                currentMessageSource.type = '---';
                messagesForDisplay.push(currentMessageSource);

                let currentMessageDestination = {};
                currentMessageDestination.id = message.messageId + "_Z";
                currentMessageDestination.messageId = '';
                currentMessageDestination = populateCurrentMessage(currentMessageDestination, entry);
                messagesForDisplay.push(currentMessageDestination);
            }
        }
    });

    return messagesForDisplay;
};

