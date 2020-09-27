@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a chat by its identifier, this is an offline request if the current user is not a bot
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChat(
    chatId: Long
) = sync<Chat>(GetChat(chatId))

suspend fun TdHandler.getChatOrNull(
    chatId: Long
) = syncOrNull<Chat>(GetChat(chatId))

fun TdHandler.getChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(GetChat(chatId), stackIgnore + 1, submit)

/**
 * Returns an ordered list of chats in a chat list
 * Chats are sorted by the pair (chat.position.order, chat.id) in descending order
 * (For example, to get a list of chats from the beginning, the offset_order should be equal to a biggest signed 64-bit number 9223372036854775807 == 2^63 - 1)
 * For optimal performance the number of returned chats is chosen by the library
 *
 * @chatList - The chat list in which to return chats
 * @offsetOrder - Chat order to return chats from
 * @offsetChatId - Chat identifier to return chats from
 * @limit - The maximum number of chats to be returned
 *          It is possible that fewer chats than the limit are returned even if the end of the list is not reached
 */
suspend fun TdHandler.getChats(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int
) = sync<Chats>(GetChats(chatList, offsetOrder, offsetChatId, limit))

suspend fun TdHandler.getChatsOrNull(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int
) = syncOrNull<Chats>(GetChats(chatList, offsetOrder, offsetChatId, limit))

fun TdHandler.getChatsWith(
    chatList: ChatList? = null,
    offsetOrder: Long,
    offsetChatId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetChats(chatList, offsetOrder, offsetChatId, limit), stackIgnore + 1, submit)

/**
 * Searches a public chat by its username
 * Currently only private chats, supergroups and channels can be public
 * Returns the chat if found
 * Otherwise an error is returned
 *
 * @username - Username to be resolved
 */
suspend fun TdHandler.searchPublicChat(
    username: String? = null
) = sync<Chat>(SearchPublicChat(username))

suspend fun TdHandler.searchPublicChatOrNull(
    username: String? = null
) = syncOrNull<Chat>(SearchPublicChat(username))

fun TdHandler.searchPublicChatWith(
    username: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(SearchPublicChat(username), stackIgnore + 1, submit)

/**
 * Searches public chats by looking for specified query in their username and title
 * Currently only private chats, supergroups and channels can be public
 * Returns a meaningful number of results
 * Returns nothing if the length of the searched username prefix is less than 5
 * Excludes private chats with contacts and chats from the chat list from the results
 *
 * @query - Query to search for
 */
suspend fun TdHandler.searchPublicChats(
    query: String? = null
) = sync<Chats>(SearchPublicChats(query))

suspend fun TdHandler.searchPublicChatsOrNull(
    query: String? = null
) = syncOrNull<Chats>(SearchPublicChats(query))

fun TdHandler.searchPublicChatsWith(
    query: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(SearchPublicChats(query), stackIgnore + 1, submit)

/**
 * Searches for the specified query in the title and username of already known chats, this is an offline request
 * Returns chats in the order seen in the main chat list
 *
 * @query - Query to search for
 *          If the query is empty, returns up to 20 recently found chats
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdHandler.searchChats(
    query: String? = null,
    limit: Int
) = sync<Chats>(SearchChats(query, limit))

suspend fun TdHandler.searchChatsOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull<Chats>(SearchChats(query, limit))

fun TdHandler.searchChatsWith(
    query: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(SearchChats(query, limit), stackIgnore + 1, submit)

/**
 * Searches for the specified query in the title and username of already known chats via request to the server
 * Returns chats in the order seen in the main chat list
 *
 * @query - Query to search for
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdHandler.searchChatsOnServer(
    query: String? = null,
    limit: Int
) = sync<Chats>(SearchChatsOnServer(query, limit))

suspend fun TdHandler.searchChatsOnServerOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull<Chats>(SearchChatsOnServer(query, limit))

fun TdHandler.searchChatsOnServerWith(
    query: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(SearchChatsOnServer(query, limit), stackIgnore + 1, submit)

/**
 * Returns a list of users and location-based supergroups nearby
 * The list of users nearby will be updated for 60 seconds after the request by the updates updateUsersNearby
 * The request should be sent again every 25 seconds with adjusted location to not miss new chats
 *
 * @location - Current user location
 */
suspend fun TdHandler.searchChatsNearby(
    location: Location? = null
) = sync<ChatsNearby>(SearchChatsNearby(location))

suspend fun TdHandler.searchChatsNearbyOrNull(
    location: Location? = null
) = syncOrNull<ChatsNearby>(SearchChatsNearby(location))

fun TdHandler.searchChatsNearbyWith(
    location: Location? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatsNearby>.() -> Unit)? = null
) = send(SearchChatsNearby(location), stackIgnore + 1, submit)

/**
 * Returns a list of frequently used chats
 * Supported only if the chat info database is enabled
 *
 * @category - Category of chats to be returned
 * @limit - The maximum number of chats to be returned
 *          Up to 30
 */
suspend fun TdHandler.getTopChats(
    category: TopChatCategory? = null,
    limit: Int
) = sync<Chats>(GetTopChats(category, limit))

suspend fun TdHandler.getTopChatsOrNull(
    category: TopChatCategory? = null,
    limit: Int
) = syncOrNull<Chats>(GetTopChats(category, limit))

fun TdHandler.getTopChatsWith(
    category: TopChatCategory? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetTopChats(category, limit), stackIgnore + 1, submit)

/**
 * Removes a chat from the list of frequently used chats
 * Supported only if the chat info database is enabled
 *
 * @category - Category of frequently used chats
 * @chatId - Chat identifier
 */
suspend fun TdHandler.removeTopChat(
    category: TopChatCategory? = null,
    chatId: Long
) = sync<Ok>(RemoveTopChat(category, chatId))

suspend fun TdHandler.removeTopChatOrNull(
    category: TopChatCategory? = null,
    chatId: Long
) = syncOrNull<Ok>(RemoveTopChat(category, chatId))

fun TdHandler.removeTopChatWith(
    category: TopChatCategory? = null,
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveTopChat(category, chatId), stackIgnore + 1, submit)

/**
 * Adds a chat to the list of recently found chats
 * The chat is added to the beginning of the list
 * If the chat is already in the list, it will be removed from the list first
 *
 * @chatId - Identifier of the chat to add
 */
suspend fun TdHandler.addRecentlyFoundChat(
    chatId: Long
) = sync<Ok>(AddRecentlyFoundChat(chatId))

suspend fun TdHandler.addRecentlyFoundChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(AddRecentlyFoundChat(chatId))

fun TdHandler.addRecentlyFoundChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddRecentlyFoundChat(chatId), stackIgnore + 1, submit)

/**
 * Removes a chat from the list of recently found chats
 *
 * @chatId - Identifier of the chat to be removed
 */
suspend fun TdHandler.removeRecentlyFoundChat(
    chatId: Long
) = sync<Ok>(RemoveRecentlyFoundChat(chatId))

suspend fun TdHandler.removeRecentlyFoundChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(RemoveRecentlyFoundChat(chatId))

fun TdHandler.removeRecentlyFoundChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveRecentlyFoundChat(chatId), stackIgnore + 1, submit)

/**
 * Clears the list of recently found chats
 */
suspend fun TdHandler.clearRecentlyFoundChats() = sync<Ok>(ClearRecentlyFoundChats())

suspend fun TdHandler.clearRecentlyFoundChatsOrNull() = syncOrNull<Ok>(ClearRecentlyFoundChats())

fun TdHandler.clearRecentlyFoundChatsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ClearRecentlyFoundChats(), stackIgnore + 1, submit)

/**
 * Returns a list of public chats of the specified type, owned by the user
 *
 * @type - Type of the public chats to return
 */
suspend fun TdHandler.getCreatedPublicChats(
    type: PublicChatType? = null
) = sync<Chats>(GetCreatedPublicChats(type))

suspend fun TdHandler.getCreatedPublicChatsOrNull(
    type: PublicChatType? = null
) = syncOrNull<Chats>(GetCreatedPublicChats(type))

fun TdHandler.getCreatedPublicChatsWith(
    type: PublicChatType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetCreatedPublicChats(type), stackIgnore + 1, submit)

/**
 * Returns a list of basic group and supergroup chats, which can be used as a discussion group for a channel
 * Basic group chats need to be first upgraded to supergroups before they can be set as a discussion group
 */
suspend fun TdHandler.getSuitableDiscussionChats() = sync<Chats>(GetSuitableDiscussionChats())

suspend fun TdHandler.getSuitableDiscussionChatsOrNull() = syncOrNull<Chats>(GetSuitableDiscussionChats())

fun TdHandler.getSuitableDiscussionChatsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetSuitableDiscussionChats(), stackIgnore + 1, submit)

/**
 * Returns a list of recently inactive supergroups and channels
 * Can be used when user reaches limit on the number of joined supergroups and channels and receives CHANNELS_TOO_MUCH error
 */
suspend fun TdHandler.getInactiveSupergroupChats() = sync<Chats>(GetInactiveSupergroupChats())

suspend fun TdHandler.getInactiveSupergroupChatsOrNull() = syncOrNull<Chats>(GetInactiveSupergroupChats())

fun TdHandler.getInactiveSupergroupChatsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetInactiveSupergroupChats(), stackIgnore + 1, submit)

/**
 * Returns a list of common group chats with a given user
 * Chats are sorted by their type and creation date
 *
 * @userId - User identifier
 * @offsetChatId - Chat identifier starting from which to return chats
 *                 Use 0 for the first request
 * @limit - The maximum number of chats to be returned
 */
suspend fun TdHandler.getGroupsInCommon(
    userId: Int,
    offsetChatId: Long,
    limit: Int
) = sync<Chats>(GetGroupsInCommon(userId, offsetChatId, limit))

suspend fun TdHandler.getGroupsInCommonOrNull(
    userId: Int,
    offsetChatId: Long,
    limit: Int
) = syncOrNull<Chats>(GetGroupsInCommon(userId, offsetChatId, limit))

fun TdHandler.getGroupsInCommonWith(
    userId: Int,
    offsetChatId: Long,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetGroupsInCommon(userId, offsetChatId, limit), stackIgnore + 1, submit)

/**
 * Deletes all messages in the chat
 * Use Chat.can_be_deleted_only_for_self and Chat.can_be_deleted_for_all_users fields to find whether and how the method can be applied to the chat
 *
 * @chatId - Chat identifier
 * @removeFromChatList - Pass true if the chat should be removed from the chat list
 * @revoke - Pass true to try to delete chat history for all users
 */
suspend fun TdHandler.deleteChatHistory(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean
) = sync<Ok>(DeleteChatHistory(chatId, removeFromChatList, revoke))

suspend fun TdHandler.deleteChatHistoryOrNull(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean
) = syncOrNull<Ok>(DeleteChatHistory(chatId, removeFromChatList, revoke))

fun TdHandler.deleteChatHistoryWith(
    chatId: Long,
    removeFromChatList: Boolean,
    revoke: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatHistory(chatId, removeFromChatList, revoke), stackIgnore + 1, submit)

/**
 * Returns approximate number of messages of the specified type in the chat
 *
 * @chatId - Identifier of the chat in which to count messages
 * @filter - Filter for message content
 *           SearchMessagesFilterEmpty is unsupported in this function
 * @returnLocal - If true, returns count that is available locally without sending network requests, returning -1 if the number of messages is unknown
 */
suspend fun TdHandler.getChatMessageCount(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean
) = sync<Count>(GetChatMessageCount(chatId, filter, returnLocal))

suspend fun TdHandler.getChatMessageCountOrNull(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean
) = syncOrNull<Count>(GetChatMessageCount(chatId, filter, returnLocal))

fun TdHandler.getChatMessageCountWith(
    chatId: Long,
    filter: SearchMessagesFilter? = null,
    returnLocal: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Count>.() -> Unit)? = null
) = send(GetChatMessageCount(chatId, filter, returnLocal), stackIgnore + 1, submit)

/**
 * Sends a notification about a screenshot taken in a chat
 * Supported only in private and secret chats
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.sendChatScreenshotTakenNotification(
    chatId: Long
) = sync<Ok>(SendChatScreenshotTakenNotification(chatId))

suspend fun TdHandler.sendChatScreenshotTakenNotificationOrNull(
    chatId: Long
) = syncOrNull<Ok>(SendChatScreenshotTakenNotification(chatId))

fun TdHandler.sendChatScreenshotTakenNotificationWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendChatScreenshotTakenNotification(chatId), stackIgnore + 1, submit)

/**
 * Deletes all messages sent by the specified user to a chat
 * Supported only for supergroups
 * Requires can_delete_messages administrator privileges
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 */
suspend fun TdHandler.deleteChatMessagesFromUser(
    chatId: Long,
    userId: Int
) = sync<Ok>(DeleteChatMessagesFromUser(chatId, userId))

suspend fun TdHandler.deleteChatMessagesFromUserOrNull(
    chatId: Long,
    userId: Int
) = syncOrNull<Ok>(DeleteChatMessagesFromUser(chatId, userId))

fun TdHandler.deleteChatMessagesFromUserWith(
    chatId: Long,
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatMessagesFromUser(chatId, userId), stackIgnore + 1, submit)

/**
 * Deletes the default reply markup from a chat
 * Must be called after a one-time keyboard or a ForceReply reply markup has been used
 * UpdateChatReplyMarkup will be sent if the reply markup will be changed
 *
 * @chatId - Chat identifier
 * @messageId - The message identifier of the used keyboard
 */
suspend fun TdHandler.deleteChatReplyMarkup(
    chatId: Long,
    messageId: Long
) = sync<Ok>(DeleteChatReplyMarkup(chatId, messageId))

suspend fun TdHandler.deleteChatReplyMarkupOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<Ok>(DeleteChatReplyMarkup(chatId, messageId))

fun TdHandler.deleteChatReplyMarkupWith(
    chatId: Long,
    messageId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatReplyMarkup(chatId, messageId), stackIgnore + 1, submit)

/**
 * Sends a notification about user activity in a chat
 *
 * @chatId - Chat identifier
 * @action - The action description
 */
suspend fun TdHandler.sendChatAction(
    chatId: Long,
    action: ChatAction? = null
) = sync<Ok>(SendChatAction(chatId, action))

suspend fun TdHandler.sendChatActionOrNull(
    chatId: Long,
    action: ChatAction? = null
) = syncOrNull<Ok>(SendChatAction(chatId, action))

fun TdHandler.sendChatActionWith(
    chatId: Long,
    action: ChatAction? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SendChatAction(chatId, action), stackIgnore + 1, submit)

/**
 * Informs TDLib that the chat is opened by the user
 * Many useful activities depend on the chat being opened or closed (e.g., in supergroups and channels all updates are received only for opened chats)
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.openChat(
    chatId: Long
) = sync<Ok>(OpenChat(chatId))

suspend fun TdHandler.openChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(OpenChat(chatId))

fun TdHandler.openChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(OpenChat(chatId), stackIgnore + 1, submit)

/**
 * Informs TDLib that the chat is closed by the user
 * Many useful activities depend on the chat being opened or closed
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.closeChat(
    chatId: Long
) = sync<Ok>(CloseChat(chatId))

suspend fun TdHandler.closeChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(CloseChat(chatId))

fun TdHandler.closeChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(CloseChat(chatId), stackIgnore + 1, submit)

/**
 * Marks all mentions in a chat as read
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.readAllChatMentions(
    chatId: Long
) = sync<Ok>(ReadAllChatMentions(chatId))

suspend fun TdHandler.readAllChatMentionsOrNull(
    chatId: Long
) = syncOrNull<Ok>(ReadAllChatMentions(chatId))

fun TdHandler.readAllChatMentionsWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReadAllChatMentions(chatId), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a given user
 *
 * @userId - User identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdHandler.createPrivateChat(
    userId: Int,
    force: Boolean
) = sync<Chat>(CreatePrivateChat(userId, force))

suspend fun TdHandler.createPrivateChatOrNull(
    userId: Int,
    force: Boolean
) = syncOrNull<Chat>(CreatePrivateChat(userId, force))

fun TdHandler.createPrivateChatWith(
    userId: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreatePrivateChat(userId, force), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a known basic group
 *
 * @basicGroupId - Basic group identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdHandler.createBasicGroupChat(
    basicGroupId: Int,
    force: Boolean
) = sync<Chat>(CreateBasicGroupChat(basicGroupId, force))

suspend fun TdHandler.createBasicGroupChatOrNull(
    basicGroupId: Int,
    force: Boolean
) = syncOrNull<Chat>(CreateBasicGroupChat(basicGroupId, force))

fun TdHandler.createBasicGroupChatWith(
    basicGroupId: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateBasicGroupChat(basicGroupId, force), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a known supergroup or channel
 *
 * @supergroupId - Supergroup or channel identifier
 * @force - If true, the chat will be created without network request
 *          In this case all information about the chat except its type, title and photo can be incorrect
 */
suspend fun TdHandler.createSupergroupChat(
    supergroupId: Int,
    force: Boolean
) = sync<Chat>(CreateSupergroupChat(supergroupId, force))

suspend fun TdHandler.createSupergroupChatOrNull(
    supergroupId: Int,
    force: Boolean
) = syncOrNull<Chat>(CreateSupergroupChat(supergroupId, force))

fun TdHandler.createSupergroupChatWith(
    supergroupId: Int,
    force: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateSupergroupChat(supergroupId, force), stackIgnore + 1, submit)

/**
 * Returns an existing chat corresponding to a known secret chat
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdHandler.createSecretChat(
    secretChatId: Int
) = sync<Chat>(CreateSecretChat(secretChatId))

suspend fun TdHandler.createSecretChatOrNull(
    secretChatId: Int
) = syncOrNull<Chat>(CreateSecretChat(secretChatId))

fun TdHandler.createSecretChatWith(
    secretChatId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateSecretChat(secretChatId), stackIgnore + 1, submit)

/**
 * Creates a new basic group and sends a corresponding messageBasicGroupChatCreate
 * Returns the newly created chat
 *
 * @userIds - Identifiers of users to be added to the basic group
 * @title - Title of the new basic group
 */
suspend fun TdHandler.createNewBasicGroupChat(
    userIds: IntArray,
    title: String? = null
) = sync<Chat>(CreateNewBasicGroupChat(userIds, title))

suspend fun TdHandler.createNewBasicGroupChatOrNull(
    userIds: IntArray,
    title: String? = null
) = syncOrNull<Chat>(CreateNewBasicGroupChat(userIds, title))

fun TdHandler.createNewBasicGroupChatWith(
    userIds: IntArray,
    title: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateNewBasicGroupChat(userIds, title), stackIgnore + 1, submit)

/**
 * Creates a new supergroup or channel and sends a corresponding messageSupergroupChatCreate
 * Returns the newly created chat
 *
 * @title - Title of the new chat
 * @isChannel - True, if a channel chat should be created
 * @description - Chat description
 * @location - Chat location if a location-based supergroup is being created
 */
suspend fun TdHandler.createNewSupergroupChat(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null
) = sync<Chat>(CreateNewSupergroupChat(title, isChannel, description, location))

suspend fun TdHandler.createNewSupergroupChatOrNull(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null
) = syncOrNull<Chat>(CreateNewSupergroupChat(title, isChannel, description, location))

fun TdHandler.createNewSupergroupChatWith(
    title: String? = null,
    isChannel: Boolean,
    description: String? = null,
    location: ChatLocation? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateNewSupergroupChat(title, isChannel, description, location), stackIgnore + 1, submit)

/**
 * Creates a new secret chat
 * Returns the newly created chat
 *
 * @userId - Identifier of the target user
 */
suspend fun TdHandler.createNewSecretChat(
    userId: Int
) = sync<Chat>(CreateNewSecretChat(userId))

suspend fun TdHandler.createNewSecretChatOrNull(
    userId: Int
) = syncOrNull<Chat>(CreateNewSecretChat(userId))

fun TdHandler.createNewSecretChatWith(
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(CreateNewSecretChat(userId), stackIgnore + 1, submit)

/**
 * Creates a new supergroup from an existing basic group and sends a corresponding messageChatUpgradeTo and messageChatUpgradeFrom
 * Requires creator privileges
 * Deactivates the original basic group
 *
 * @chatId - Identifier of the chat to upgrade
 */
suspend fun TdHandler.upgradeBasicGroupChatToSupergroupChat(
    chatId: Long
) = sync<Chat>(UpgradeBasicGroupChatToSupergroupChat(chatId))

suspend fun TdHandler.upgradeBasicGroupChatToSupergroupChatOrNull(
    chatId: Long
) = syncOrNull<Chat>(UpgradeBasicGroupChatToSupergroupChat(chatId))

fun TdHandler.upgradeBasicGroupChatToSupergroupChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(UpgradeBasicGroupChatToSupergroupChat(chatId), stackIgnore + 1, submit)

/**
 * Returns chat lists to which the chat can be added
 * This is an offline request
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChatListsToAddChat(
    chatId: Long
) = sync<ChatLists>(GetChatListsToAddChat(chatId))

suspend fun TdHandler.getChatListsToAddChatOrNull(
    chatId: Long
) = syncOrNull<ChatLists>(GetChatListsToAddChat(chatId))

fun TdHandler.getChatListsToAddChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatLists>.() -> Unit)? = null
) = send(GetChatListsToAddChat(chatId), stackIgnore + 1, submit)

/**
 * Adds a chat to a chat list
 * A chat can't be simultaneously in Main and Archive chat lists, so it is automatically removed from another one if needed
 *
 * @chatId - Chat identifier
 * @chatList - The chat list
 *             Use getChatListsToAddChat to get suitable chat lists
 */
suspend fun TdHandler.addChatToList(
    chatId: Long,
    chatList: ChatList? = null
) = sync<Ok>(AddChatToList(chatId, chatList))

suspend fun TdHandler.addChatToListOrNull(
    chatId: Long,
    chatList: ChatList? = null
) = syncOrNull<Ok>(AddChatToList(chatId, chatList))

fun TdHandler.addChatToListWith(
    chatId: Long,
    chatList: ChatList? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddChatToList(chatId, chatList), stackIgnore + 1, submit)

/**
 * Returns information about a chat filter by its identifier
 *
 * @chatFilterId - Chat filter identifier
 */
suspend fun TdHandler.getChatFilter(
    chatFilterId: Int
) = sync<ChatFilter>(GetChatFilter(chatFilterId))

suspend fun TdHandler.getChatFilterOrNull(
    chatFilterId: Int
) = syncOrNull<ChatFilter>(GetChatFilter(chatFilterId))

fun TdHandler.getChatFilterWith(
    chatFilterId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatFilter>.() -> Unit)? = null
) = send(GetChatFilter(chatFilterId), stackIgnore + 1, submit)

/**
 * Creates new chat filter
 * Returns information about the created chat filter
 *
 * @filter - Chat filter
 */
suspend fun TdHandler.createChatFilter(
    filter: ChatFilter? = null
) = sync<ChatFilterInfo>(CreateChatFilter(filter))

suspend fun TdHandler.createChatFilterOrNull(
    filter: ChatFilter? = null
) = syncOrNull<ChatFilterInfo>(CreateChatFilter(filter))

fun TdHandler.createChatFilterWith(
    filter: ChatFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatFilterInfo>.() -> Unit)? = null
) = send(CreateChatFilter(filter), stackIgnore + 1, submit)

/**
 * Edits existing chat filter
 * Returns information about the edited chat filter
 *
 * @chatFilterId - Chat filter identifier
 * @filter - The edited chat filter
 */
suspend fun TdHandler.editChatFilter(
    chatFilterId: Int,
    filter: ChatFilter? = null
) = sync<ChatFilterInfo>(EditChatFilter(chatFilterId, filter))

suspend fun TdHandler.editChatFilterOrNull(
    chatFilterId: Int,
    filter: ChatFilter? = null
) = syncOrNull<ChatFilterInfo>(EditChatFilter(chatFilterId, filter))

fun TdHandler.editChatFilterWith(
    chatFilterId: Int,
    filter: ChatFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatFilterInfo>.() -> Unit)? = null
) = send(EditChatFilter(chatFilterId, filter), stackIgnore + 1, submit)

/**
 * Deletes existing chat filter
 *
 * @chatFilterId - Chat filter identifier
 */
suspend fun TdHandler.deleteChatFilter(
    chatFilterId: Int
) = sync<Ok>(DeleteChatFilter(chatFilterId))

suspend fun TdHandler.deleteChatFilterOrNull(
    chatFilterId: Int
) = syncOrNull<Ok>(DeleteChatFilter(chatFilterId))

fun TdHandler.deleteChatFilterWith(
    chatFilterId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteChatFilter(chatFilterId), stackIgnore + 1, submit)

/**
 * Changes the order of chat filters
 *
 * @chatFilterIds - Identifiers of chat filters in the new correct order
 */
suspend fun TdHandler.reorderChatFilters(
    chatFilterIds: IntArray
) = sync<Ok>(ReorderChatFilters(chatFilterIds))

suspend fun TdHandler.reorderChatFiltersOrNull(
    chatFilterIds: IntArray
) = syncOrNull<Ok>(ReorderChatFilters(chatFilterIds))

fun TdHandler.reorderChatFiltersWith(
    chatFilterIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReorderChatFilters(chatFilterIds), stackIgnore + 1, submit)

/**
 * Changes the chat title
 * Supported only for basic groups, supergroups and channels
 * Requires can_change_info rights
 *
 * @chatId - Chat identifier
 * @title - New title of the chat
 */
suspend fun TdHandler.setChatTitle(
    chatId: Long,
    title: String? = null
) = sync<Ok>(SetChatTitle(chatId, title))

suspend fun TdHandler.setChatTitleOrNull(
    chatId: Long,
    title: String? = null
) = syncOrNull<Ok>(SetChatTitle(chatId, title))

fun TdHandler.setChatTitleWith(
    chatId: Long,
    title: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatTitle(chatId, title), stackIgnore + 1, submit)

/**
 * Changes the photo of a chat
 * Supported only for basic groups, supergroups and channels
 * Requires can_change_info rights
 *
 * @chatId - Chat identifier
 * @photo - New chat photo
 *          You can pass null to delete the chat photo
 */
suspend fun TdHandler.setChatPhoto(
    chatId: Long,
    photo: InputChatPhoto? = null
) = sync<Ok>(SetChatPhoto(chatId, photo))

suspend fun TdHandler.setChatPhotoOrNull(
    chatId: Long,
    photo: InputChatPhoto? = null
) = syncOrNull<Ok>(SetChatPhoto(chatId, photo))

fun TdHandler.setChatPhotoWith(
    chatId: Long,
    photo: InputChatPhoto? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatPhoto(chatId, photo), stackIgnore + 1, submit)

/**
 * Changes the chat members permissions
 * Supported only for basic groups and supergroups
 * Requires can_restrict_members administrator right
 *
 * @chatId - Chat identifier
 * @permissions - New non-administrator members permissions in the chat
 */
suspend fun TdHandler.setChatPermissions(
    chatId: Long,
    permissions: ChatPermissions? = null
) = sync<Ok>(SetChatPermissions(chatId, permissions))

suspend fun TdHandler.setChatPermissionsOrNull(
    chatId: Long,
    permissions: ChatPermissions? = null
) = syncOrNull<Ok>(SetChatPermissions(chatId, permissions))

fun TdHandler.setChatPermissionsWith(
    chatId: Long,
    permissions: ChatPermissions? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatPermissions(chatId, permissions), stackIgnore + 1, submit)

/**
 * Changes the draft message in a chat
 *
 * @chatId - Chat identifier
 * @draftMessage - New draft message
 */
suspend fun TdHandler.setChatDraftMessage(
    chatId: Long,
    draftMessage: DraftMessage? = null
) = sync<Ok>(SetChatDraftMessage(chatId, draftMessage))

suspend fun TdHandler.setChatDraftMessageOrNull(
    chatId: Long,
    draftMessage: DraftMessage? = null
) = syncOrNull<Ok>(SetChatDraftMessage(chatId, draftMessage))

fun TdHandler.setChatDraftMessageWith(
    chatId: Long,
    draftMessage: DraftMessage? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatDraftMessage(chatId, draftMessage), stackIgnore + 1, submit)

/**
 * Changes the notification settings of a chat
 * Notification settings of a chat with the current user (Saved Messages) can't be changed
 *
 * @chatId - Chat identifier
 * @notificationSettings - New notification settings for the chat
 *                         If the chat is muted for more than 1 week, it is considered to be muted forever
 */
suspend fun TdHandler.setChatNotificationSettings(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null
) = sync<Ok>(SetChatNotificationSettings(chatId, notificationSettings))

suspend fun TdHandler.setChatNotificationSettingsOrNull(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null
) = syncOrNull<Ok>(SetChatNotificationSettings(chatId, notificationSettings))

fun TdHandler.setChatNotificationSettingsWith(
    chatId: Long,
    notificationSettings: ChatNotificationSettings? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatNotificationSettings(chatId, notificationSettings), stackIgnore + 1, submit)

/**
 * Changes the marked as unread state of a chat
 *
 * @chatId - Chat identifier
 * @isMarkedAsUnread - New value of is_marked_as_unread
 */
suspend fun TdHandler.toggleChatIsMarkedAsUnread(
    chatId: Long,
    isMarkedAsUnread: Boolean
) = sync<Ok>(ToggleChatIsMarkedAsUnread(chatId, isMarkedAsUnread))

suspend fun TdHandler.toggleChatIsMarkedAsUnreadOrNull(
    chatId: Long,
    isMarkedAsUnread: Boolean
) = syncOrNull<Ok>(ToggleChatIsMarkedAsUnread(chatId, isMarkedAsUnread))

fun TdHandler.toggleChatIsMarkedAsUnreadWith(
    chatId: Long,
    isMarkedAsUnread: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleChatIsMarkedAsUnread(chatId, isMarkedAsUnread), stackIgnore + 1, submit)

/**
 * Changes the value of the default disable_notification parameter, used when a message is sent to a chat
 *
 * @chatId - Chat identifier
 * @defaultDisableNotification - New value of default_disable_notification
 */
suspend fun TdHandler.toggleChatDefaultDisableNotification(
    chatId: Long,
    defaultDisableNotification: Boolean
) = sync<Ok>(ToggleChatDefaultDisableNotification(chatId, defaultDisableNotification))

suspend fun TdHandler.toggleChatDefaultDisableNotificationOrNull(
    chatId: Long,
    defaultDisableNotification: Boolean
) = syncOrNull<Ok>(ToggleChatDefaultDisableNotification(chatId, defaultDisableNotification))

fun TdHandler.toggleChatDefaultDisableNotificationWith(
    chatId: Long,
    defaultDisableNotification: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleChatDefaultDisableNotification(chatId, defaultDisableNotification), stackIgnore + 1, submit)

/**
 * Changes application-specific data associated with a chat
 *
 * @chatId - Chat identifier
 * @clientData - New value of client_data
 */
suspend fun TdHandler.setChatClientData(
    chatId: Long,
    clientData: String? = null
) = sync<Ok>(SetChatClientData(chatId, clientData))

suspend fun TdHandler.setChatClientDataOrNull(
    chatId: Long,
    clientData: String? = null
) = syncOrNull<Ok>(SetChatClientData(chatId, clientData))

fun TdHandler.setChatClientDataWith(
    chatId: Long,
    clientData: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatClientData(chatId, clientData), stackIgnore + 1, submit)

/**
 * Changes information about a chat
 * Available for basic groups, supergroups, and channels
 * Requires can_change_info rights
 *
 * @chatId - Identifier of the chat
 * @description - New chat description
 */
suspend fun TdHandler.setChatDescription(
    chatId: Long,
    description: String? = null
) = sync<Ok>(SetChatDescription(chatId, description))

suspend fun TdHandler.setChatDescriptionOrNull(
    chatId: Long,
    description: String? = null
) = syncOrNull<Ok>(SetChatDescription(chatId, description))

fun TdHandler.setChatDescriptionWith(
    chatId: Long,
    description: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatDescription(chatId, description), stackIgnore + 1, submit)

/**
 * Changes the discussion group of a channel chat
 * Requires can_change_info rights in the channel if it is specified
 *
 * @chatId - Identifier of the channel chat
 *           Pass 0 to remove a link from the supergroup passed in the second argument to a linked channel chat (requires can_pin_messages rights in the supergroup)
 * @discussionChatId - Identifier of a new channel's discussion group
 *                     Use 0 to remove the discussion group
 *                     Use the method getSuitableDiscussionChats to find all suitable groups
 *                     Basic group chats needs to be first upgraded to supergroup chats
 *                     If new chat members don't have access to old messages in the supergroup, then toggleSupergroupIsAllHistoryAvailable needs to be used first to change that
 */
suspend fun TdHandler.setChatDiscussionGroup(
    chatId: Long,
    discussionChatId: Long
) = sync<Ok>(SetChatDiscussionGroup(chatId, discussionChatId))

suspend fun TdHandler.setChatDiscussionGroupOrNull(
    chatId: Long,
    discussionChatId: Long
) = syncOrNull<Ok>(SetChatDiscussionGroup(chatId, discussionChatId))

fun TdHandler.setChatDiscussionGroupWith(
    chatId: Long,
    discussionChatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatDiscussionGroup(chatId, discussionChatId), stackIgnore + 1, submit)

/**
 * Changes the location of a chat
 * Available only for some location-based supergroups, use supergroupFullInfo.can_set_location to check whether the method is allowed to use
 *
 * @chatId - Chat identifier
 * @location - New location for the chat
 *             Must be valid and not null
 */
suspend fun TdHandler.setChatLocation(
    chatId: Long,
    location: ChatLocation? = null
) = sync<Ok>(SetChatLocation(chatId, location))

suspend fun TdHandler.setChatLocationOrNull(
    chatId: Long,
    location: ChatLocation? = null
) = syncOrNull<Ok>(SetChatLocation(chatId, location))

fun TdHandler.setChatLocationWith(
    chatId: Long,
    location: ChatLocation? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatLocation(chatId, location), stackIgnore + 1, submit)

/**
 * Changes the slow mode delay of a chat
 * Available only for supergroups
 * Requires can_restrict_members rights
 *
 * @chatId - Chat identifier
 * @slowModeDelay - New slow mode delay for the chat
 *                  Must be one of 0, 10, 30, 60, 300, 900, 3600
 */
suspend fun TdHandler.setChatSlowModeDelay(
    chatId: Long,
    slowModeDelay: Int
) = sync<Ok>(SetChatSlowModeDelay(chatId, slowModeDelay))

suspend fun TdHandler.setChatSlowModeDelayOrNull(
    chatId: Long,
    slowModeDelay: Int
) = syncOrNull<Ok>(SetChatSlowModeDelay(chatId, slowModeDelay))

fun TdHandler.setChatSlowModeDelayWith(
    chatId: Long,
    slowModeDelay: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatSlowModeDelay(chatId, slowModeDelay), stackIgnore + 1, submit)

/**
 * Pins a message in a chat
 * Requires can_pin_messages rights
 *
 * @chatId - Identifier of the chat
 * @messageId - Identifier of the new pinned message
 * @disableNotification - True, if there should be no notification about the pinned message
 */
suspend fun TdHandler.pinChatMessage(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean
) = sync<Ok>(PinChatMessage(chatId, messageId, disableNotification))

suspend fun TdHandler.pinChatMessageOrNull(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean
) = syncOrNull<Ok>(PinChatMessage(chatId, messageId, disableNotification))

fun TdHandler.pinChatMessageWith(
    chatId: Long,
    messageId: Long,
    disableNotification: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(PinChatMessage(chatId, messageId, disableNotification), stackIgnore + 1, submit)

/**
 * Removes the pinned message from a chat
 * Requires can_pin_messages rights in the group or channel
 *
 * @chatId - Identifier of the chat
 */
suspend fun TdHandler.unpinChatMessage(
    chatId: Long
) = sync<Ok>(UnpinChatMessage(chatId))

suspend fun TdHandler.unpinChatMessageOrNull(
    chatId: Long
) = syncOrNull<Ok>(UnpinChatMessage(chatId))

fun TdHandler.unpinChatMessageWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(UnpinChatMessage(chatId), stackIgnore + 1, submit)

/**
 * Adds current user as a new member to a chat
 * Private and secret chats can't be joined using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.joinChat(
    chatId: Long
) = sync<Ok>(JoinChat(chatId))

suspend fun TdHandler.joinChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(JoinChat(chatId))

fun TdHandler.joinChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(JoinChat(chatId), stackIgnore + 1, submit)

/**
 * Removes current user from chat members
 * Private and secret chats can't be left using this method
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.leaveChat(
    chatId: Long
) = sync<Ok>(LeaveChat(chatId))

suspend fun TdHandler.leaveChatOrNull(
    chatId: Long
) = syncOrNull<Ok>(LeaveChat(chatId))

fun TdHandler.leaveChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(LeaveChat(chatId), stackIgnore + 1, submit)

/**
 * Adds a new member to a chat
 * Members can't be added to private or secret chats
 * Members will not be added until the chat state has been synchronized with the server
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user
 * @forwardLimit - The number of earlier messages from the chat to be forwarded to the new member
 *                 Ignored for supergroups and channels
 */
suspend fun TdHandler.addChatMember(
    chatId: Long,
    userId: Int,
    forwardLimit: Int
) = sync<Ok>(AddChatMember(chatId, userId, forwardLimit))

suspend fun TdHandler.addChatMemberOrNull(
    chatId: Long,
    userId: Int,
    forwardLimit: Int
) = syncOrNull<Ok>(AddChatMember(chatId, userId, forwardLimit))

fun TdHandler.addChatMemberWith(
    chatId: Long,
    userId: Int,
    forwardLimit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddChatMember(chatId, userId, forwardLimit), stackIgnore + 1, submit)

/**
 * Adds multiple new members to a chat
 * Currently this option is only available for supergroups and channels
 * This option can't be used to join a chat
 * Members can't be added to a channel if it has more than 200 members
 * Members will not be added until the chat state has been synchronized with the server
 *
 * @chatId - Chat identifier
 * @userIds - Identifiers of the users to be added to the chat
 */
suspend fun TdHandler.addChatMembers(
    chatId: Long,
    userIds: IntArray
) = sync<Ok>(AddChatMembers(chatId, userIds))

suspend fun TdHandler.addChatMembersOrNull(
    chatId: Long,
    userIds: IntArray
) = syncOrNull<Ok>(AddChatMembers(chatId, userIds))

fun TdHandler.addChatMembersWith(
    chatId: Long,
    userIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(AddChatMembers(chatId, userIds), stackIgnore + 1, submit)

/**
 * Changes the status of a chat member, needs appropriate privileges
 * This function is currently not suitable for adding new members to the chat and transferring chat ownership
 * Instead, use addChatMember or transferChatOwnership
 * The chat member status will not be changed until it has been synchronized with the server
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 * @status - The new status of the member in the chat
 */
suspend fun TdHandler.setChatMemberStatus(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null
) = sync<Ok>(SetChatMemberStatus(chatId, userId, status))

suspend fun TdHandler.setChatMemberStatusOrNull(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null
) = syncOrNull<Ok>(SetChatMemberStatus(chatId, userId, status))

fun TdHandler.setChatMemberStatusWith(
    chatId: Long,
    userId: Int,
    status: ChatMemberStatus? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetChatMemberStatus(chatId, userId, status), stackIgnore + 1, submit)

/**
 * Changes the owner of a chat
 * The current user must be a current owner of the chat
 * Use the method canTransferOwnership to check whether the ownership can be transferred from the current session
 * Available only for supergroups and channel chats
 *
 * @chatId - Chat identifier
 * @userId - Identifier of the user to which transfer the ownership
 *           The ownership can't be transferred to a bot or to a deleted user
 * @password - The password of the current user
 */
suspend fun TdHandler.transferChatOwnership(
    chatId: Long,
    userId: Int,
    password: String? = null
) = sync<Ok>(TransferChatOwnership(chatId, userId, password))

suspend fun TdHandler.transferChatOwnershipOrNull(
    chatId: Long,
    userId: Int,
    password: String? = null
) = syncOrNull<Ok>(TransferChatOwnership(chatId, userId, password))

fun TdHandler.transferChatOwnershipWith(
    chatId: Long,
    userId: Int,
    password: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(TransferChatOwnership(chatId, userId, password), stackIgnore + 1, submit)

/**
 * Returns information about a single member of a chat
 *
 * @chatId - Chat identifier
 * @userId - User identifier
 */
suspend fun TdHandler.getChatMember(
    chatId: Long,
    userId: Int
) = sync<ChatMember>(GetChatMember(chatId, userId))

suspend fun TdHandler.getChatMemberOrNull(
    chatId: Long,
    userId: Int
) = syncOrNull<ChatMember>(GetChatMember(chatId, userId))

fun TdHandler.getChatMemberWith(
    chatId: Long,
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatMember>.() -> Unit)? = null
) = send(GetChatMember(chatId, userId), stackIgnore + 1, submit)

/**
 * Searches for a specified query in the first name, last name and username of the members of a specified chat
 * Requires administrator rights in channels
 *
 * @chatId - Chat identifier
 * @query - Query to search for
 * @limit - The maximum number of users to be returned
 * @filter - The type of users to return
 *           By default, chatMembersFilterMembers
 */
suspend fun TdHandler.searchChatMembers(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null
) = sync<ChatMembers>(SearchChatMembers(chatId, query, limit, filter))

suspend fun TdHandler.searchChatMembersOrNull(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null
) = syncOrNull<ChatMembers>(SearchChatMembers(chatId, query, limit, filter))

fun TdHandler.searchChatMembersWith(
    chatId: Long,
    query: String? = null,
    limit: Int,
    filter: ChatMembersFilter? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatMembers>.() -> Unit)? = null
) = send(SearchChatMembers(chatId, query, limit, filter), stackIgnore + 1, submit)

/**
 * Returns a list of administrators of the chat with their custom titles
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.getChatAdministrators(
    chatId: Long
) = sync<ChatAdministrators>(GetChatAdministrators(chatId))

suspend fun TdHandler.getChatAdministratorsOrNull(
    chatId: Long
) = syncOrNull<ChatAdministrators>(GetChatAdministrators(chatId))

fun TdHandler.getChatAdministratorsWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatAdministrators>.() -> Unit)? = null
) = send(GetChatAdministrators(chatId), stackIgnore + 1, submit)

/**
 * Returns list of chats with non-default notification settings
 *
 * @scope - If specified, only chats from the specified scope will be returned
 * @compareSound - If true, also chats with non-default sound will be returned
 */
suspend fun TdHandler.getChatNotificationSettingsExceptions(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean
) = sync<Chats>(GetChatNotificationSettingsExceptions(scope, compareSound))

suspend fun TdHandler.getChatNotificationSettingsExceptionsOrNull(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean
) = syncOrNull<Chats>(GetChatNotificationSettingsExceptions(scope, compareSound))

fun TdHandler.getChatNotificationSettingsExceptionsWith(
    scope: NotificationSettingsScope? = null,
    compareSound: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chats>.() -> Unit)? = null
) = send(GetChatNotificationSettingsExceptions(scope, compareSound), stackIgnore + 1, submit)

/**
 * Changes the pinned state of a chat
 * You can pin up to GetOption("pinned_chat_count_max")/GetOption("pinned_archived_chat_count_max") non-secret chats and the same number of secret chats in the main/arhive chat list
 *
 * @chatList - Chat list in which to change the pinned state of the chat
 * @chatId - Chat identifier
 * @isPinned - True, if the chat is pinned
 */
suspend fun TdHandler.toggleChatIsPinned(
    chatList: ChatList? = null,
    chatId: Long,
    isPinned: Boolean
) = sync<Ok>(ToggleChatIsPinned(chatList, chatId, isPinned))

suspend fun TdHandler.toggleChatIsPinnedOrNull(
    chatList: ChatList? = null,
    chatId: Long,
    isPinned: Boolean
) = syncOrNull<Ok>(ToggleChatIsPinned(chatList, chatId, isPinned))

fun TdHandler.toggleChatIsPinnedWith(
    chatList: ChatList? = null,
    chatId: Long,
    isPinned: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleChatIsPinned(chatList, chatId, isPinned), stackIgnore + 1, submit)

/**
 * Changes the order of pinned chats
 *
 * @chatList - Chat list in which to change the order of pinned chats
 * @chatIds - The new list of pinned chats
 */
suspend fun TdHandler.setPinnedChats(
    chatList: ChatList? = null,
    chatIds: LongArray
) = sync<Ok>(SetPinnedChats(chatList, chatIds))

suspend fun TdHandler.setPinnedChatsOrNull(
    chatList: ChatList? = null,
    chatIds: LongArray
) = syncOrNull<Ok>(SetPinnedChats(chatList, chatIds))

fun TdHandler.setPinnedChatsWith(
    chatList: ChatList? = null,
    chatIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetPinnedChats(chatList, chatIds), stackIgnore + 1, submit)

/**
 * Generates a new invite link for a chat
 * The previously generated link is revoked
 * Available for basic groups, supergroups, and channels
 * Requires administrator privileges and can_invite_users right
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.generateChatInviteLink(
    chatId: Long
) = sync<ChatInviteLink>(GenerateChatInviteLink(chatId))

suspend fun TdHandler.generateChatInviteLinkOrNull(
    chatId: Long
) = syncOrNull<ChatInviteLink>(GenerateChatInviteLink(chatId))

fun TdHandler.generateChatInviteLinkWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLink>.() -> Unit)? = null
) = send(GenerateChatInviteLink(chatId), stackIgnore + 1, submit)

/**
 * Checks the validity of an invite link for a chat and returns information about the corresponding chat
 *
 * @inviteLink - Invite link to be checked
 */
suspend fun TdHandler.checkChatInviteLink(
    inviteLink: String? = null
) = sync<ChatInviteLinkInfo>(CheckChatInviteLink(inviteLink))

suspend fun TdHandler.checkChatInviteLinkOrNull(
    inviteLink: String? = null
) = syncOrNull<ChatInviteLinkInfo>(CheckChatInviteLink(inviteLink))

fun TdHandler.checkChatInviteLinkWith(
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatInviteLinkInfo>.() -> Unit)? = null
) = send(CheckChatInviteLink(inviteLink), stackIgnore + 1, submit)

/**
 * Uses an invite link to add the current user to the chat if possible
 * The new member will not be added until the chat state has been synchronized with the server
 *
 * @inviteLink - Invite link to import
 */
suspend fun TdHandler.joinChatByInviteLink(
    inviteLink: String? = null
) = sync<Chat>(JoinChatByInviteLink(inviteLink))

suspend fun TdHandler.joinChatByInviteLinkOrNull(
    inviteLink: String? = null
) = syncOrNull<Chat>(JoinChatByInviteLink(inviteLink))

fun TdHandler.joinChatByInviteLinkWith(
    inviteLink: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Chat>.() -> Unit)? = null
) = send(JoinChatByInviteLink(inviteLink), stackIgnore + 1, submit)

/**
 * Returns the profile photos of a user
 * The result of this query may be outdated: some photos might have been deleted already
 *
 * @userId - User identifier
 * @offset - The number of photos to skip
 * @limit - The maximum number of photos to be returned
 */
suspend fun TdHandler.getUserProfilePhotos(
    userId: Int,
    offset: Int,
    limit: Int
) = sync<ChatPhotos>(GetUserProfilePhotos(userId, offset, limit))

suspend fun TdHandler.getUserProfilePhotosOrNull(
    userId: Int,
    offset: Int,
    limit: Int
) = syncOrNull<ChatPhotos>(GetUserProfilePhotos(userId, offset, limit))

fun TdHandler.getUserProfilePhotosWith(
    userId: Int,
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatPhotos>.() -> Unit)? = null
) = send(GetUserProfilePhotos(userId, offset, limit), stackIgnore + 1, submit)

/**
 * Returns information about members or banned users in a supergroup or channel
 * Can be used only if SupergroupFullInfo.can_get_members == true
 * Additionally, administrator privileges may be required for some filters
 *
 * @supergroupId - Identifier of the supergroup or channel
 * @filter - The type of users to return
 *           By default, supergroupMembersRecent
 * @offset - Number of users to skip
 * @limit - The maximum number of users be returned
 *          Up to 200
 */
suspend fun TdHandler.getSupergroupMembers(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int
) = sync<ChatMembers>(GetSupergroupMembers(supergroupId, filter, offset, limit))

suspend fun TdHandler.getSupergroupMembersOrNull(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int
) = syncOrNull<ChatMembers>(GetSupergroupMembers(supergroupId, filter, offset, limit))

fun TdHandler.getSupergroupMembersWith(
    supergroupId: Int,
    filter: SupergroupMembersFilter? = null,
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatMembers>.() -> Unit)? = null
) = send(GetSupergroupMembers(supergroupId, filter, offset, limit), stackIgnore + 1, submit)

/**
 * Returns a list of service actions taken by chat members and administrators in the last 48 hours
 * Available only for supergroups and channels
 * Requires administrator rights
 * Returns results in reverse chronological order (i
 * E., in order of decreasing event_id)
 *
 * @chatId - Chat identifier
 * @query - Search query by which to filter events
 * @fromEventId - Identifier of an event from which to return results
 *                Use 0 to get results from the latest events
 * @limit - The maximum number of events to return
 * @filters - The types of events to return
 *            By default, all types will be returned
 * @userIds - User identifiers by which to filter events
 *            By default, events relating to all users will be returned
 */
suspend fun TdHandler.getChatEventLog(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray
) = sync<ChatEvents>(GetChatEventLog(chatId, query, fromEventId, limit, filters, userIds))

suspend fun TdHandler.getChatEventLogOrNull(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray
) = syncOrNull<ChatEvents>(GetChatEventLog(chatId, query, fromEventId, limit, filters, userIds))

fun TdHandler.getChatEventLogWith(
    chatId: Long,
    query: String? = null,
    fromEventId: Long,
    limit: Int,
    filters: ChatEventLogFilters? = null,
    userIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatEvents>.() -> Unit)? = null
) = send(GetChatEventLog(chatId, query, fromEventId, limit, filters, userIds), stackIgnore + 1, submit)

/**
 * Removes a chat action bar without any other action
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.removeChatActionBar(
    chatId: Long
) = sync<Ok>(RemoveChatActionBar(chatId))

suspend fun TdHandler.removeChatActionBarOrNull(
    chatId: Long
) = syncOrNull<Ok>(RemoveChatActionBar(chatId))

fun TdHandler.removeChatActionBarWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RemoveChatActionBar(chatId), stackIgnore + 1, submit)

/**
 * Reports a chat to the Telegram moderators
 * A chat can be reported only from the chat action bar, or if this is a private chats with a bot, a private chat with a user sharing their location, a supergroup, or a channel, since other chats can't be checked by moderators
 *
 * @chatId - Chat identifier
 * @reason - The reason for reporting the chat
 * @messageIds - Identifiers of reported messages, if any
 */
suspend fun TdHandler.reportChat(
    chatId: Long,
    reason: ChatReportReason? = null,
    messageIds: LongArray
) = sync<Ok>(ReportChat(chatId, reason, messageIds))

suspend fun TdHandler.reportChatOrNull(
    chatId: Long,
    reason: ChatReportReason? = null,
    messageIds: LongArray
) = syncOrNull<Ok>(ReportChat(chatId, reason, messageIds))

fun TdHandler.reportChatWith(
    chatId: Long,
    reason: ChatReportReason? = null,
    messageIds: LongArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ReportChat(chatId, reason, messageIds), stackIgnore + 1, submit)

/**
 * Returns an HTTP URL with the chat statistics
 * Currently this method of getting the statistics is disabled and can be deleted in the future
 *
 * @chatId - Chat identifier
 * @parameters - Parameters from "tg://statsrefresh?params=******" link
 * @isDark - Pass true if a URL with the dark theme must be returned
 */
suspend fun TdHandler.getChatStatisticsUrl(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean
) = sync<HttpUrl>(GetChatStatisticsUrl(chatId, parameters, isDark))

suspend fun TdHandler.getChatStatisticsUrlOrNull(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean
) = syncOrNull<HttpUrl>(GetChatStatisticsUrl(chatId, parameters, isDark))

fun TdHandler.getChatStatisticsUrlWith(
    chatId: Long,
    parameters: String? = null,
    isDark: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetChatStatisticsUrl(chatId, parameters, isDark), stackIgnore + 1, submit)

/**
 * Returns detailed statistics about a chat
 * Currently this method can be used only for supergroups and channels
 * Requires administrator rights in the channel
 *
 * @chatId - Chat identifier
 * @isDark - Pass true if a dark theme is used by the app
 */
suspend fun TdHandler.getChatStatistics(
    chatId: Long,
    isDark: Boolean
) = sync<ChatStatistics>(GetChatStatistics(chatId, isDark))

suspend fun TdHandler.getChatStatisticsOrNull(
    chatId: Long,
    isDark: Boolean
) = syncOrNull<ChatStatistics>(GetChatStatistics(chatId, isDark))

fun TdHandler.getChatStatisticsWith(
    chatId: Long,
    isDark: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<ChatStatistics>.() -> Unit)? = null
) = send(GetChatStatistics(chatId, isDark), stackIgnore + 1, submit)